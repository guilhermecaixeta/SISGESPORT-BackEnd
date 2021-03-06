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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import com.ifg.sistema.sisgesport.api.entities.commom_entities.EntidadeComum;
import com.ifg.sistema.sisgesport.api.entities.commom_entities.Pessoa;

@Entity
@Table(name="partida")
public class Partida extends EntidadeComum  implements Serializable {

	private static final long serialVersionUID = -6820184287589704577L;
	
	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Long id;
	
	@Column(name="data_partida")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPartida;
	
	@Column(name="duracao_partida")
	private double duracaoPartida;
	
	@Column(name="acrescimo")
	private double acrescimo;
	
	@ManyToOne
	@JoinColumn(name="juiz", referencedColumnName="id", nullable=false, foreignKey = @ForeignKey(name="fk_juiz_partida"))
	@NotNull(message="O campo juiz não pode ser nulo.")
	private Pessoa juiz;
	
	@ManyToOne
	@JoinColumn(name="time_casa", referencedColumnName="id", nullable=false, foreignKey = @ForeignKey(name="fk_time_casa_partida"))
	private Time timeCasa;
	
	@ManyToOne
	@JoinColumn(name="time_visita", referencedColumnName="id", nullable=false, foreignKey = @ForeignKey(name="fk_time_visita_partida"))
	private Time timeVisita;
	
	@ManyToOne
	@JoinColumn(name="evento", referencedColumnName="id", nullable=false, foreignKey = @ForeignKey(name="fk_evento_partida"))
	private Evento evento;
	
	@ManyToOne
	@JoinColumn(name="evento_modalidade", referencedColumnName="id", nullable=false, foreignKey = @ForeignKey(name="fk_evento_modalidade"))
	@NotNull(message="O campo evento modalidade não pode ser nulo.")
	private EventoModalidade eventoModalidade;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="partida")
	private List<PartidaPenalidade> partidaPenalidade = new ArrayList<PartidaPenalidade>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="partida")
	private List<PartidaPonto> partidaPonto = new ArrayList<PartidaPonto>();

	public Partida() {	}

	public void AdicionarPartidaPenalidade(PartidaPenalidade obj) {
	obj.setPartida(this);
	this.partidaPenalidade.add(obj);
	}

	public void RemoverPartidaPenalidade(int id) {
		this.partidaPenalidade.remove(id);
	}
	
	public void AdicionarPartidaPonto(PartidaPonto obj) {
	obj.setPartida(this);
	this.partidaPonto.add(obj);
	}

	public void RemoverPartidaPonto(int id) {
		this.partidaPonto.remove(id);
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

	public EventoModalidade getEventoModalidade() {
		return eventoModalidade;
	}

	public void setEventoModalidade(EventoModalidade eventoModalidade) {
		this.eventoModalidade = eventoModalidade;
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
