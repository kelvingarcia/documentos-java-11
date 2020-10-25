package org.fatec.scs.documentos.model;

import org.fatec.scs.documentos.enums.FormatoDocumento;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Documento {
	
	@Id
	private String id;
	private String nome;
	private byte[] arquivo;
	private byte[] arquivoAssinado;
		
	public Documento() {
	}

	public Documento(String nome, byte[] arquivo) {
		this.nome = nome;
		this.arquivo = arquivo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte[] getArquivoAssinado() {
		return arquivoAssinado;
	}

	public void setArquivoAssinado(byte[] arquivoAssinado) {
		this.arquivoAssinado = arquivoAssinado;
	}
}
