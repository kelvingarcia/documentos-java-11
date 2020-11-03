package org.fatec.scs.documentos.handler;

import org.fatec.scs.documentos.dto.ReconhecimentoToken;
import org.fatec.scs.documentos.dto.request.ReconheceRequest;
import org.fatec.scs.documentos.dto.request.TreinaRequest;
import org.fatec.scs.documentos.dto.response.PessoaDTO;
import org.fatec.scs.documentos.dto.response.PredicaoConfianca;
import org.fatec.scs.documentos.dto.response.Reconhecimento;
import org.fatec.scs.documentos.security.JwtSigner;
import org.fatec.scs.documentos.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
public class DocumentoHandler {

    @Autowired
    private DocumentoService documentoService;

    @Autowired
    private JwtSigner jwtSigner;

    public Mono<ServerResponse> reconhecePessoa(ReconheceRequest reconheceRequest){
        return documentoService.reconhecePessoa(reconheceRequest)
            .flatMap(reconhecimento -> {
                var jwt = jwtSigner.createJwt(reconhecimento.getPessoa().getId());
                return ok()
                        .body(Mono.just(new ReconhecimentoToken(reconhecimento, jwt)), ReconhecimentoToken.class);
            });
    }

    public Mono<ServerResponse> reconhecePessoaTeste(){
        Reconhecimento reconhecimento = new Reconhecimento();
        reconhecimento.setPessoa(new PessoaDTO("5f938b99b38f29186ec7f7d3", "Kelvin", "kelvin@email.com", 1));
        reconhecimento.setPredicaoConfianca(new PredicaoConfianca(1, 90.0, 20, 20L));
        reconhecimento.setStatusReconhecimento("Reconhecimento corretamento");
        var jwt = jwtSigner.createJwt(reconhecimento.getPessoa().getId());
        return ok()
                .body(Mono.just(new ReconhecimentoToken(reconhecimento, jwt)), ReconhecimentoToken.class);
    }

    public Mono<ServerResponse> cadastraPessoa(TreinaRequest treinaRequest){
        return documentoService.cadastraPessoa(treinaRequest)
                .flatMap(pessoaDTO -> ok().body(Mono.just(pessoaDTO), PessoaDTO.class));
    }
}
