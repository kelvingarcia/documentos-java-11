package org.fatec.scs.documentos.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Pasta {
	@Id
	private String id;
	private String nomePasta;
	private String descricao;
	private List<String> administradores = new ArrayList<>();
	private List<String> membros;
	private List<String> documentos = new ArrayList<>();
	
	public Pasta() {
	}

	public Pasta(String nomePasta, String admin, String descricao, List<String> membros) {
		this.nomePasta = nomePasta;
		this.descricao = descricao;
		this.membros = membros;
		this.administradores.add(admin);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNomePasta() {
		return nomePasta;
	}
	public void setNomePasta(String nomePasta) {
		this.nomePasta = nomePasta;
	}
	public List<String> getAdministradores() {
		return administradores;
	}
	public void setAdministradores(List<String> administradores) {
		this.administradores = administradores;
	}
	public List<String> getMembros() {
		return membros;
	}
	public void setMembros(List<String> membros) {
		this.membros = membros;
	}
	public List<String> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(List<String> documentos) {
		this.documentos = documentos;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Pasta addDocumento(String documento) {
		this.documentos.add(documento);
		return this;
	}
	
}
