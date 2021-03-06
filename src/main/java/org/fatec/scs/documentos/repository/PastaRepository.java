package org.fatec.scs.documentos.repository;

import org.fatec.scs.documentos.model.Pasta;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface PastaRepository extends ReactiveCrudRepository<Pasta, String> {
    Flux<Pasta> findByMembrosContaining(String id);
}
