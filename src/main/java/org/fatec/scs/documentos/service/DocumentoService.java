package org.fatec.scs.documentos.service;

import org.fatec.scs.documentos.dto.DocumentoDTO;
import org.fatec.scs.documentos.dto.PastaDTO;
import org.fatec.scs.documentos.dto.PastaResponse;
import org.fatec.scs.documentos.dto.request.ReconheceRequest;
import org.fatec.scs.documentos.dto.request.TreinaRequest;
import org.fatec.scs.documentos.dto.response.PessoaDTO;
import org.fatec.scs.documentos.dto.response.Reconhecimento;
import org.fatec.scs.documentos.enums.FormatoDocumento;
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

import java.util.List;
import java.util.stream.Collectors;

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
			.flatMap(pastaDTO -> pastaRepository.save(new Pasta(pastaDTO.getNome(), idUsuario, pastaDTO.getDescricao(), List.of(idUsuario))))
			.map(pasta -> new PastaResponse(pasta));
	}

	public Flux<PastaResponse> listaDePastas(String idUsuario){
		return pastaRepository.findByMembrosContaining(idUsuario).map(pasta -> new PastaResponse(pasta));
	}

	public Mono<Pasta> salvarDocumento(DocumentoDTO documentoDto) {
		return documentoRepository.save(new Documento(documentoDto.getNome(), documentoDto.getArquivo()))
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
}
