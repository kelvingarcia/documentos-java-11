package org.fatec.scs.documentos.model;

import java.util.ArrayList;
import java.util.List;

public class ArquivoAssinado {
    private List<byte[]> arquivoAssinado = new ArrayList<>();
    private String assinador;

    public String getAssinador() {
        return assinador;
    }

    public void setAssinador(String assinador) {
        this.assinador = assinador;
    }

    public List<byte[]> getArquivoAssinado() {
        return arquivoAssinado;
    }

    public void setArquivoAssinado(List<byte[]> arquivoAssinado) {
        this.arquivoAssinado = arquivoAssinado;
    }
}
