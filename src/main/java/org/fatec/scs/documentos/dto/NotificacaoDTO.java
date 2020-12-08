package org.fatec.scs.documentos.dto;

import org.fatec.scs.documentos.model.Notificacao;

import java.time.format.DateTimeFormatter;

public class NotificacaoDTO {
    private String texto;
    private String idPasta;
    private String tipo;
    private String horario;

    public NotificacaoDTO() {
    }

    public NotificacaoDTO(Notificacao notificacao) {
        this.texto = notificacao.getTexto();
        this.tipo = notificacao.getTipoNotificacao().name();
        this.idPasta = "";
        this.horario = notificacao.getHorario().toString().substring(0, 5);
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getIdPasta() {
        return idPasta;
    }

    public void setIdPasta(String idPasta) {
        this.idPasta = idPasta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
