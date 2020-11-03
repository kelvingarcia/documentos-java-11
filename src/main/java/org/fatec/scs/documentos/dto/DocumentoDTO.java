package org.fatec.scs.documentos.dto;

import org.fatec.scs.documentos.model.Documento;

import java.time.format.DateTimeFormatter;

public class DocumentoDTO {
    private String nome;
    private byte[] arquivo;
    private String descricao;
    private String dataHora;
    private String idPasta;

    public DocumentoDTO() {
    }

    public DocumentoDTO(Documento documento){
        this.nome = documento.getNome();
        this.arquivo = documento.getArquivo();
        this.descricao = documento.getDescricao();
        this.dataHora = documento.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIdPasta() {
        return idPasta;
    }

    public void setIdPasta(String idPasta) {
        this.idPasta = idPasta;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }
}
