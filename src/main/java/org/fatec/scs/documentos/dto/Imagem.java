package org.fatec.scs.documentos.dto;

public class Imagem {
    private byte[] arquivo;

    public Imagem() {
    }

    public Imagem(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }
}
