package com.ifg.sistema.sisgesport.api.dto.dto_retorno;

public class EquipeRetornoDTO {
    private Long id;
    private String nome;
    private EventoRetornoDTO evento;

    public EquipeRetornoDTO(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventoRetornoDTO getEvento() {
        return evento;
    }

    public void setEvento(EventoRetornoDTO evento) {
        this.evento = evento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
