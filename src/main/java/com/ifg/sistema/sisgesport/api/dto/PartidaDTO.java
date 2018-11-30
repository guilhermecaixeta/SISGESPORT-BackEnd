package com.ifg.sistema.sisgesport.api.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ifg.sistema.sisgesport.api.dto.commom_entities.EntidadeComumDTO;
import com.ifg.sistema.sisgesport.api.dto.commom_entities.PessoaDTO;
import com.ifg.sistema.sisgesport.api.dto.dto_retorno.TimeRetornoDTO;
import com.ifg.sistema.sisgesport.api.entities.Evento;
import com.ifg.sistema.sisgesport.api.entities.Modalidade;
import com.ifg.sistema.sisgesport.api.entities.PartidaPenalidade;
import com.ifg.sistema.sisgesport.api.entities.PartidaPonto;
import com.ifg.sistema.sisgesport.api.entities.Time;
import com.ifg.sistema.sisgesport.api.entities.commom_entities.Pessoa;

public class PartidaDTO extends EntidadeComumDTO {
	private Long id;
	private Date dataPartida;
	private double duracaoPartida;
	private double acrescimo;
	private PessoaDTO juiz;
	private TimeRetornoDTO timeCasa;
	private TimeRetornoDTO timeVisita;
	private EventoDTO evento;
	private ModalidadeDTO modalidade;
	private List<PartidaPenalidadeDTO> partidaPenalidade = new ArrayList<PartidaPenalidadeDTO>();
	private List<PartidaPontoDTO> partidaPonto = new ArrayList<PartidaPontoDTO>();

	public PartidaDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataPartida() {
		return dataPartida;
	}

	public void setDataPartida(Date dataPartida) {
		this.dataPartida = dataPartida;
	}

	public double getDuracaoPartida() {
		return duracaoPartida;
	}

	public void setDuracaoPartida(double duracaoPartida) {
		this.duracaoPartida = duracaoPartida;
	}

	public double getAcrescimo() {
		return acrescimo;
	}

	public void setAcrescimo(double acrescimo) {
		this.acrescimo = acrescimo;
	}

	public PessoaDTO getJuiz() {
		return juiz;
	}

	public void setJuiz(PessoaDTO juiz) {
		this.juiz = juiz;
	}

	public TimeRetornoDTO getTimeCasa() {
		return timeCasa;
	}

	public void setTimeCasa(TimeRetornoDTO timeCasa) {
		this.timeCasa = timeCasa;
	}

	public TimeRetornoDTO getTimeVisita() {
		return timeVisita;
	}

	public void setTimeVisita(TimeRetornoDTO timeVisita) {
		this.timeVisita = timeVisita;
	}

	public EventoDTO getEvento() {
		return evento;
	}

	public void setEvento(EventoDTO evento) {
		this.evento = evento;
	}

	public ModalidadeDTO getModalidade() {
		return modalidade;
	}

	public void setModalidade(ModalidadeDTO modalidade) {
		this.modalidade = modalidade;
	}

	public List<PartidaPenalidadeDTO> getPartidaPenalidade() {
		return partidaPenalidade;
	}

	public void setPartidaPenalidade(List<PartidaPenalidadeDTO> partidaPenalidade) {
		this.partidaPenalidade = partidaPenalidade;
	}

	public List<PartidaPontoDTO> getPartidaPonto() {
		return partidaPonto;
	}

	public void setPartidaPonto(List<PartidaPontoDTO> partidaPonto) {
		this.partidaPonto = partidaPonto;
	}

}
