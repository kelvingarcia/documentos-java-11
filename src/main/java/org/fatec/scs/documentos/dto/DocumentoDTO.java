package org.fatec.scs.documentos.dto;

public class DocumentoDTO {
    private String nome;
    private byte[] arquivo;
    private String idPasta;

    public DocumentoDTO(String nome, byte[] arquivo, String idPasta) {
        this.nome = nome;
        this.arquivo = arquivo;
        this.idPasta = idPasta;
    }

    public DocumentoDTO() {
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public String getIdPasta() {
        return idPasta;
    }

    public void setIdPasta(String idPasta) {
        this.idPasta = idPasta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
