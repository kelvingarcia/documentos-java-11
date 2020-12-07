package org.fatec.scs.documentos.model;

public class ArquivoAssinado {
    private byte[] arquivoAssinado;
    private String assinador;

    public String getAssinador() {
        return assinador;
    }

    public void setAssinador(String assinador) {
        this.assinador = assinador;
    }

    public byte[] getArquivoAssinado() {
        return arquivoAssinado;
    }

    public void setArquivoAssinado(byte[] arquivoAssinado) {
        this.arquivoAssinado = arquivoAssinado;
    }
}
