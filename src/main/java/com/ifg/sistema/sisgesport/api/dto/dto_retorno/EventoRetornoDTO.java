package com.ifg.sistema.sisgesport.api.dto.dto_retorno;

import java.util.List;

public class EventoRetornoDTO {
    private Long id;
    private List<EquipeRetornoDTO> equipe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<EquipeRetornoDTO> getEquipe() {
        return equipe;
    }

    public void setEquipe(List<EquipeRetornoDTO> equipe) {
        this.equipe = equipe;
    }
}
