package org.fatec.scs.documentos.repository;

import org.fatec.scs.documentos.model.Documento;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface DocumentoRepository extends ReactiveCrudRepository<Documento, String> {

}
