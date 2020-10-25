package org.fatec.scs.documentos.dto.response;

public class Reconhecimento {
    private PessoaDTO pessoa;
    private PredicaoConfianca predicaoConfianca;
    private String statusReconhecimento;

    public Reconhecimento() {
    }

    public PessoaDTO getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaDTO pessoa) {
        this.pessoa = pessoa;
    }

    public PredicaoConfianca getPredicaoConfianca() {
        return predicaoConfianca;
    }

    public void setPredicaoConfianca(PredicaoConfianca predicaoConfianca) {
        this.predicaoConfianca = predicaoConfianca;
    }

    public String getStatusReconhecimento() {
        return statusReconhecimento;
    }

    public void setStatusReconhecimento(String statusReconhecimento) {
        this.statusReconhecimento = statusReconhecimento;
    }
}
