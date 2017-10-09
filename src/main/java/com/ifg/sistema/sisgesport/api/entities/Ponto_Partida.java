package com.ifg.sistema.sisgesport.api.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ponto_partida")
public class Ponto_Partida implements Serializable {

	private static final long serialVersionUID = 31L;
	
	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Long id;

	@ManyToOne
	@JoinColumn(name="jogador", referencedColumnName="id", nullable=false)
	@NotNull(message="O campo jogador não pode ser nulo.")
	private Jogador jogador;
	
	@ManyToOne
	@JoinColumn(name="partida", referencedColumnName="id", nullable=false)
	@NotNull(message="O campo partida não pode ser nulo.")
	private Partida partida;
	
	@ManyToOne
	@JoinColumn(name="tipo_ponto", referencedColumnName="id", nullable=false)
	private Tipo_Ponto tipo_ponto;

	public Ponto_Partida() {	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public Tipo_Ponto getTipo_ponto() {
		return tipo_ponto;
	}

	public void setTipo_ponto(Tipo_Ponto tipo_ponto) {
		this.tipo_ponto = tipo_ponto;
	}
}
