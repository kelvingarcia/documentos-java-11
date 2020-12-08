package org.fatec.scs.documentos.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;
import org.fatec.scs.documentos.dto.*;
import org.fatec.scs.documentos.dto.request.ReconheceRequest;
import org.fatec.scs.documentos.dto.request.TreinaRequest;
import org.fatec.scs.documentos.dto.response.DocumentoList;
import org.fatec.scs.documentos.dto.response.PessoaDTO;
import org.fatec.scs.documentos.dto.response.Reconhecimento;
import org.fatec.scs.documentos.enums.FormatoDocumento;
import org.fatec.scs.documentos.model.ArquivoAssinado;
import org.fatec.scs.documentos.model.Documento;
import org.fatec.scs.documentos.model.Pasta;
import org.fatec.scs.documentos.model.Pessoa;
import org.fatec.scs.documentos.repository.DocumentoRepository;
import org.fatec.scs.documentos.repository.PastaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DocumentoService {

	@Autowired
	private DocumentoRepository documentoRepository;
	
	@Autowired
	private PastaRepository pastaRepository;
	
	@Autowired
	private WebClient webClient;
	
	public Mono<PastaResponse> criaNovaPasta(String idUsuario, ServerRequest req){
		return req.bodyToMono(PastaDTO.class)
			.flatMap(pastaDTO -> pastaRepository.save(new Pasta(pastaDTO.getId(), pastaDTO.getNome(), idUsuario, pastaDTO.getDescricao(), pastaDTO.getEmails())))
			.map(pasta -> new PastaResponse(pasta));
	}

	public Flux<PastaResponse> listaDePastas(String email){
		return pastaRepository.findByMembrosContaining(email)
				.filter(pasta -> pasta.isStatusAtivo())
				.map(pasta -> new PastaResponse(pasta));
	}

	public Mono<Pasta> salvarDocumento(DocumentoDTO documentoDto) {
		return documentoRepository.save(new Documento(documentoDto))
			.flatMap(documento -> pastaRepository.findById(documentoDto.getIdPasta()).flatMap(pasta -> pastaRepository.save(pasta.addDocumento(documento.getId()))));
	}

	public Mono<PessoaDTO> cadastraPessoa(TreinaRequest treinaRequest){
		return this.webClient
				.post()
				.uri("/envioTreinamento")
				.body(Mono.just(treinaRequest), TreinaRequest.class)
				.retrieve().bodyToMono(PessoaDTO.class);
	}

	public Mono<Reconhecimento> reconhecePessoa(ReconheceRequest reconheceRequest){
		return this.webClient
				.post()
				.uri("/reconhecePessoa")
				.body(Mono.just(reconheceRequest), ReconheceRequest.class)
				.retrieve().bodyToMono(Reconhecimento.class);
	}

	public Mono<PessoaDTO> usuarioLogado(String name){
		System.out.println(name);
		return this.webClient
				.get()
				.uri("/pessoa/{id}", name)
				.retrieve().bodyToMono(PessoaDTO.class);
	}
	
	public Mono<Pessoa> buscaPorEmail(String email) {
		return this.webClient
				.get()
				.uri("/email/{email}", email)
				.retrieve()
				.bodyToMono(Pessoa.class);
	}

	public Flux<DocumentoList> documentosNaPasta(String idPasta, String email){
		return this.pastaRepository.findById(idPasta)
				.flatMapMany(pasta -> this.documentoRepository.findAllById(pasta.getDocumentos()))
				.filter(documento -> documento.getAssinantes().contains(email))
				.map(documento -> {
					List<ArquivoAssinado> assinados = documento.getArquivoAssinados().stream().filter(arquivoAssinado -> arquivoAssinado.getAssinador().equals(email)).collect(Collectors.toList());
					return new DocumentoList(documento, !assinados.isEmpty());
				});
	}

	public Mono<Pasta> getUmaPasta(String id){
		return this.pastaRepository.findById(id);
	}

	public Mono<Pasta> desativarPasta(String id) {
		return this.pastaRepository.findById(id).flatMap(pasta -> {
			pasta.setStatusAtivo(false);
			return this.pastaRepository.save(pasta);
		});
	}

	public Mono<Paginas> imagemDoDocumento(String idDocumento, String email){
		return documentoRepository.findById(idDocumento)
			.map(documento -> {
				Paginas paginas = new Paginas();
				List<ArquivoAssinado> assinados = documento.getArquivoAssinados().stream().filter(arquivoAssinado -> arquivoAssinado.getAssinador().equals(email)).collect(Collectors.toList());
				if(!assinados.isEmpty()){
					paginas.setArquivo(assinados.get(0).getArquivoAssinado().stream().map(bytes -> new Imagem(bytes)).collect(Collectors.toList()));
					return paginas;
				} else {
					try {
						PDDocument document = PDDocument.load(documento.getArquivo());
						PDFRenderer pdfRenderer = new PDFRenderer(document);
						for (int page = 0; page < document.getNumberOfPages(); ++page) {
							BufferedImage bim = pdfRenderer.renderImageWithDPI(
									page, 300, ImageType.RGB);
							ByteArrayOutputStream baos = new ByteArrayOutputStream();
							ImageIO.write(bim, "png", baos);
							baos.flush();
							paginas.getArquivo().add(new Imagem(baos.toByteArray()));
							baos.close();
						}
						document.close();
						return paginas;
					} catch (Exception e) {
						e.printStackTrace();
						return paginas;
					}
				}
			});
	}

	public Mono<DocumentoList> assinaDocumento(String id, ArquivoAssinado arquivoAssinado){
		return documentoRepository.findById(id)
				.map(documento -> documento.addArquivoAssinado(arquivoAssinado))
				.flatMap(documento -> documentoRepository.save(documento))
				.map(documento -> new DocumentoList(documento));
	}
}
