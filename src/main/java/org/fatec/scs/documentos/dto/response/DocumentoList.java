package org.fatec.scs.documentos.dto.response;

import org.fatec.scs.documentos.model.Documento;

import java.time.format.DateTimeFormatter;

public class DocumentoList {
    private String id;
    private String nome;
    private String descricao;
    private String dataHora;

    public DocumentoList() {
    }

    public DocumentoList(Documento documento) {
        this.id = documento.getId();
        this.nome = documento.getNome();
        this.descricao = documento.getDescricao();
        this.dataHora = documento.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
