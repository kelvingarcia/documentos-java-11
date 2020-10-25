package org.fatec.scs.documentos.router;

import org.fatec.scs.documentos.dto.DocumentoDTO;
import org.fatec.scs.documentos.dto.PastaResponse;
import org.fatec.scs.documentos.dto.request.ReconheceRequest;
import org.fatec.scs.documentos.dto.request.TreinaRequest;
import org.fatec.scs.documentos.dto.response.PessoaDTO;
import org.fatec.scs.documentos.handler.DocumentoHandler;
import org.fatec.scs.documentos.model.Pasta;
import org.fatec.scs.documentos.model.Pessoa;
import org.fatec.scs.documentos.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.security.Principal;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class DocumentoRouter {

	@Autowired
	private DocumentoService documentoService;

	@Autowired
	private DocumentoHandler documentoHandler;
	
	@Bean
	public RouterFunction<ServerResponse> routes(){
		return route()
		.POST("/documento", req -> 
			ok().body(req.bodyToMono(DocumentoDTO.class).flatMap(dto ->
			documentoService.salvarDocumento(dto)),
			Pasta.class
		))
		.POST("/pasta", req ->
			ok().body(req.principal().map(Principal::getName).flatMap(name ->
			documentoService.criaNovaPasta(name, req)),
			PastaResponse.class
		))
		.POST("/reconhecePessoa", req ->
			req.bodyToMono(ReconheceRequest.class).flatMap(reconheceRequest ->
					documentoHandler.reconhecePessoa(reconheceRequest)
		))
		.POST("/cadastraPessoa", req ->
			req.bodyToMono(TreinaRequest.class).flatMap(treinaRequest ->
					documentoHandler.cadastraPessoa(treinaRequest)
		))
		.GET("/usuarioLogado", req ->
			ok().body(req.principal().map(Principal::getName).flatMap(name ->
			documentoService.usuarioLogado(name)),
			PessoaDTO.class
		))
		.GET("/listaDePastas", req ->
			ok().body(req.principal().map(Principal::getName).flatMapMany(name ->
			documentoService.listaDePastas(name)),
			PastaResponse.class
		))
		.GET("/buscaPorEmail/{email}", req ->
			ok().body(documentoService.buscaPorEmail(req.pathVariable("email")),
			Pessoa.class
		))
		.build();
	}
}
