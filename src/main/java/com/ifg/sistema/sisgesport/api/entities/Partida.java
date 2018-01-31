package com.ifg.sistema.sisgesport.api.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="partida")
public class Partida  implements Serializable {

	private static final long serialVersionUID = 3L;
	
	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Integer id;
	
	@Column(name="data_partida")
	private Calendar data_partida;
	
	@Column(name="periodos")
	private int periodos;

	@Column(name="duracao_periodo")
	private int duracao_periodo;
	
	@Column(name="acrescimo")
	private int acrescimo;
	
	@ManyToOne
	@JoinColumn(name="juiz", referencedColumnName="id", nullable=false, foreignKey = @ForeignKey(name="fk_juiz_partida"))
	@NotNull(message="O campo juiz não pode ser nulo.")
	private Pessoa juiz;
	
	@ManyToOne
	@JoinColumn(name="endereco", referencedColumnName="id", nullable=false, foreignKey = @ForeignKey(name="fk_endereco_partida"))
	@NotNull(message="O campo endereco não pode ser nulo.")
	private Endereco endereco;
	
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

	public Partida() {	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getPeriodos() {
		return periodos;
	}

	public void setPeriodos(int periodos) {
		this.periodos = periodos;
	}

	public int getDuracao_periodo() {
		return duracao_periodo;
	}

	public void setDuracao_periodo(int duracao_periodo) {
		this.duracao_periodo = duracao_periodo;
	}

	public int getAcrescimo() {
		return acrescimo;
	}

	public void setAcrescimo(int acrescimo) {
		this.acrescimo = acrescimo;
	}

	public Pessoa getJuiz() {
		return juiz;
	}

	public void setJuiz(Pessoa juiz) {
		this.juiz = juiz;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
	
}
