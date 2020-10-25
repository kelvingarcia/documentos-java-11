package org.fatec.scs.documentos.dto.response;

public class PredicaoConfianca {
    private int predicao;
    private double confianca;
    private int numeroDeFotos;
    private Long fotoReconhecidas;

    public PredicaoConfianca(int predicao, double confianca, int numeroDeFotos, Long fotoReconhecidas) {
        this.predicao = predicao;
        this.confianca = confianca;
        this.numeroDeFotos = numeroDeFotos;
        this.fotoReconhecidas = fotoReconhecidas;
    }

    public PredicaoConfianca() {
    }

    public int getPredicao() {
        return predicao;
    }

    public void setPredicao(int predicao) {
        this.predicao = predicao;
    }

    public double getConfianca() {
        return confianca;
    }

    public void setConfianca(double confianca) {
        this.confianca = confianca;
    }

    public int getNumeroDeFotos() {
        return numeroDeFotos;
    }

    public void setNumeroDeFotos(int numeroDeFotos) {
        this.numeroDeFotos = numeroDeFotos;
    }

    public Long getFotoReconhecidas() {
        return fotoReconhecidas;
    }

    public void setFotoReconhecidas(Long fotoReconhecidas) {
        this.fotoReconhecidas = fotoReconhecidas;
    }
}
