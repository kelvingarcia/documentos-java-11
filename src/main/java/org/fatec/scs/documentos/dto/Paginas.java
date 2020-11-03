package org.fatec.scs.documentos.dto;

import java.util.ArrayList;
import java.util.List;

public class Paginas {
    private List<Imagem> arquivo = new ArrayList<>();

    public List<Imagem> getArquivo() {
        return arquivo;
    }

    public void setArquivo(List<Imagem> arquivo) {
        this.arquivo = arquivo;
    }
}
