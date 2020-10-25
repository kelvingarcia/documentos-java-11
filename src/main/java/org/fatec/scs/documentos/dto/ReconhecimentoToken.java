package org.fatec.scs.documentos.dto;

import org.fatec.scs.documentos.dto.response.Reconhecimento;

public class ReconhecimentoToken {
    private Reconhecimento reconhecimento;
    private String token;

    public ReconhecimentoToken() {
    }

    public ReconhecimentoToken(Reconhecimento reconhecimento, String token) {
        this.reconhecimento = reconhecimento;
        this.token = token;
    }

    public Reconhecimento getReconhecimento() {
        return reconhecimento;
    }

    public void setReconhecimento(Reconhecimento reconhecimento) {
        this.reconhecimento = reconhecimento;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
