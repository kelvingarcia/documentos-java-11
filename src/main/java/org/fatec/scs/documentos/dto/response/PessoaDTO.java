package org.fatec.scs.documentos.dto.response;

public class PessoaDTO {
    private String id;
    private String nome;
    private String email;
    private int classe;

    public PessoaDTO(String id, String nome, String email, int classe) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.classe = classe;
    }

    public PessoaDTO() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getClasse() {
        return classe;
    }

    public void setClasse(int classe) {
        this.classe = classe;
    }
}