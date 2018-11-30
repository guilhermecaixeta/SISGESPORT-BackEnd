package com.ifg.sistema.sisgesport.api.dto.dto_retorno;

public class TimeRetornoDTO {
    private Long id;
    private EquipeSimpleRetornoDTO equipe;
    private ModalidadeRetornoDTO modalidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ModalidadeRetornoDTO getModalidade() {
        return modalidade;
    }

    public void setModalidade(ModalidadeRetornoDTO modalidade) {
        this.modalidade = modalidade;
    }

    public EquipeSimpleRetornoDTO getEquipe() {
        return equipe;
    }

    public void setEquipe(EquipeSimpleRetornoDTO equipe) {
        this.equipe = equipe;
    }
}
