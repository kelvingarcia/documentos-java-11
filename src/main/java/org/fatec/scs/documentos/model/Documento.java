package org.fatec.scs.documentos.model;

import org.fatec.scs.documentos.dto.DocumentoDTO;
import org.fatec.scs.documentos.enums.FormatoDocumento;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
public class Documento {
	
	@Id
	private String id;
	private String nome;
	private LocalDateTime dataHora;
	private String descricao;
	private byte[] arquivo;
	private List<ArquivoAssinado> arquivoAssinados = new ArrayList<>();
	private List<String> assinantes = new ArrayList<>();

	public Documento() {
	}

	public Documento(DocumentoDTO documentoDTO){
		this.nome = documentoDTO.getNome();
		this.descricao = documentoDTO.getDescricao();
		this.arquivo = documentoDTO.getArquivo();
		this.dataHora = LocalDateTime.now();
		this.assinantes = documentoDTO.getAssinantes();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public List<ArquivoAssinado> getArquivoAssinados() {
		return arquivoAssinados;
	}

	public void setArquivoAssinados(List<ArquivoAssinado> arquivoAssinados) {
		this.arquivoAssinados = arquivoAssinados;
	}

	public List<String> getAssinantes() {
		return assinantes;
	}

	public void setAssinantes(List<String> assinantes) {
		this.assinantes = assinantes;
	}

	public Documento addArquivoAssinado(ArquivoAssinado arquivoAssinado){
		this.arquivoAssinados.add(arquivoAssinado);
		return this;
	}
}
