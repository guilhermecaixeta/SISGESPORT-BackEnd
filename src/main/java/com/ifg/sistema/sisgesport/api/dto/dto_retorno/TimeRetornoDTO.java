package com.ifg.sistema.sisgesport.api.dto.dto_retorno;

import com.ifg.sistema.sisgesport.api.dto.EventoModalidadeDTO;

public class TimeRetornoDTO {
    private Long id;
    private EquipeSimpleRetornoDTO equipe;
    private EventoModalidadeDTO eventoModalidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EquipeSimpleRetornoDTO getEquipe() {
        return equipe;
    }

    public void setEquipe(EquipeSimpleRetornoDTO equipe) {
        this.equipe = equipe;
    }


    public EventoModalidadeDTO getEventoModalidade() {
        return eventoModalidade;
    }

    public void setEventoModalidade(EventoModalidadeDTO eventoModalidade) {
        this.eventoModalidade = eventoModalidade;
    }
}
