package org.fatec.scs.documentos.model;

import org.fatec.scs.documentos.enums.TipoNotificacao;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Document
public class Notificacao {
    @Id
    private String id;
    private List<String> membros = new ArrayList<>();
    private String texto;
    private TipoNotificacao tipoNotificacao;
    private LocalTime horario;

    public Notificacao() {
    }

    public Notificacao(List<String> membros, String texto, String tipo) {
        this.membros = membros;
        this.texto = texto;
        this.tipoNotificacao = TipoNotificacao.valueOf(tipo);
        this.horario = LocalTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getMembros() {
        return membros;
    }

    public void setMembros(List<String> membros) {
        this.membros = membros;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public TipoNotificacao getTipoNotificacao() {
        return tipoNotificacao;
    }

    public void setTipoNotificacao(TipoNotificacao tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }
}
