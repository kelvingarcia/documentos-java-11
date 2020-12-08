package org.fatec.scs.documentos.repository;

import org.fatec.scs.documentos.model.Notificacao;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface NotificacaoRepository extends ReactiveCrudRepository<Notificacao, String> {
    Flux<Notificacao> findByMembrosContaining(String id);
}
