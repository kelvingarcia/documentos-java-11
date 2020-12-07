package org.fatec.scs.documentos.dto;

import java.util.List;

public class PastaDTO {
    private String id;
    private String nome;
    private String descricao;
    private List<String> emails;

    public PastaDTO(String nome, String descricao, List<String> emails) {
        this.nome = nome;
        this.descricao = descricao;
        this.emails = emails;
    }

    public PastaDTO() {
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

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
