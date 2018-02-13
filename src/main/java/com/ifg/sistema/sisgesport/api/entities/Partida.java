package com.ifg.sistema.sisgesport.api.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.ifg.sistema.sisgesport.api.entities.commom_entities.Entidade_Comum;

@Entity
@Table(name="partida")
public class Partida extends Entidade_Comum  implements Serializable {

	private static final long serialVersionUID = -6820184287589704577L;

	@Column(name="data_partida")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_partida;
	
	@Column(name="duracao_partida")
	private double duracao_partida;
	
	@Column(name="acrescimo")
	private double acrescimo;
	
	@ManyToOne
	@JoinColumn(name="juiz", referencedColumnName="id", nullable=false, foreignKey = @ForeignKey(name="fk_juiz_partida"))
	@NotNull(message="O campo juiz não pode ser nulo.")
	private Pessoa juiz;
	
	@ManyToOne
	@JoinColumn(name="time_casa", referencedColumnName="id", nullable=false, foreignKey = @ForeignKey(name="fk_time_casa_partida"))
	private Time time_casa;
	
	@ManyToOne
	@JoinColumn(name="time_visita", referencedColumnName="id", nullable=false, foreignKey = @ForeignKey(name="fk_time_visita_partida"))
	private Time time_visita;
	
	@ManyToOne
	@JoinColumn(name="evento", referencedColumnName="id", nullable=false, foreignKey = @ForeignKey(name="fk_evento_partida"))
	private Evento evento;
	
	@ManyToOne
	@JoinColumn(name="modalidade", referencedColumnName="id", nullable=false, foreignKey = @ForeignKey(name="fk_evento_modalidade"))
	@NotNull(message="O campo modalidade não pode ser nulo.")
	private Modalidade modalidade;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="partida")
	private List<Partida_Penalidade> partida_penalidade = new ArrayList<Partida_Penalidade>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="partida")
	private List<Partida_Ponto> partida_ponto = new ArrayList<Partida_Ponto>();

	public Partida() {	}

	public void AdicionarPartidaPenalidade(Partida_Penalidade obj) {
	obj.setPartida(this);
	this.partida_penalidade.add(obj);
	}

	public void RemoverPartidaPenalidade(int id) {
		this.partida_penalidade.remove(id);
	}
	
	public void AdicionarPartidaPonto(Partida_Ponto obj) {
	obj.setPartida(this);
	this.partida_ponto.add(obj);
	}

	public void RemoverPartidaPonto(int id) {
		this.partida_ponto.remove(id);
	}

	public Date getData_partida() {
		return data_partida;
	}

	public void setData_partida(Date data_partida) {
		this.data_partida = data_partida;
	}

	public double getDuracao_partida() {
		return duracao_partida;
	}

	public void setDuracao_partida(double duracao_partida) {
		this.duracao_partida = duracao_partida;
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

	public Time getTime_casa() {
		return time_casa;
	}

	public void setTime_casa(Time time_casa) {
		this.time_casa = time_casa;
	}

	public Time getTime_visita() {
		return time_visita;
	}

	public void setTime_visita(Time time_visita) {
		this.time_visita = time_visita;
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

	public List<Partida_Penalidade> getPartida_penalidade() {
		return partida_penalidade;
	}

	public void setPartida_penalidade(List<Partida_Penalidade> partida_penalidade) {
		this.partida_penalidade = partida_penalidade;
	}

	public List<Partida_Ponto> getPartida_ponto() {
		return partida_ponto;
	}

	public void setPartida_ponto(List<Partida_Ponto> partida_ponto) {
		this.partida_ponto = partida_ponto;
	}
	
}
