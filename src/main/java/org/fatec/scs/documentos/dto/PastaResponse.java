package org.fatec.scs.documentos.dto;

import org.fatec.scs.documentos.model.Pasta;

public class PastaResponse {
    private String id;
    private String nome;
    private String descricao;

    public PastaResponse() {
    }

    public PastaResponse(Pasta pasta) {
        this.id = pasta.getId();
        this.nome = pasta.getNomePasta();
        this.descricao = pasta.getDescricao();
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
