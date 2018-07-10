package com.ifg.sistema.sisgesport.api.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ifg.sistema.sisgesport.api.dto.commom_entities.EntidadeComumDTO;
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
	private Pessoa juiz;
	private Time timeCasa;
	private Time timeVisita;
	private Evento evento;
	private Modalidade modalidade;
	private List<PartidaPenalidade> partidaPenalidade = new ArrayList<PartidaPenalidade>();
	private List<PartidaPonto> partidaPonto = new ArrayList<PartidaPonto>();

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

	public Pessoa getJuiz() {
		return juiz;
	}

	public void setJuiz(Pessoa juiz) {
		this.juiz = juiz;
	}

	public Time getTimeCasa() {
		return timeCasa;
	}

	public void setTimeCasa(Time timeCasa) {
		this.timeCasa = timeCasa;
	}

	public Time getTimeVisita() {
		return timeVisita;
	}

	public void setTimeVisita(Time timeVisita) {
		this.timeVisita = timeVisita;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Modalidade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}

	public List<PartidaPenalidade> getPartidaPenalidade() {
		return partidaPenalidade;
	}

	public void setPartidaPenalidade(List<PartidaPenalidade> partidaPenalidade) {
		this.partidaPenalidade = partidaPenalidade;
	}

	public List<PartidaPonto> getPartidaPonto() {
		return partidaPonto;
	}

	public void setPartidaPonto(List<PartidaPonto> partidaPonto) {
		this.partidaPonto = partidaPonto;
	}

}
