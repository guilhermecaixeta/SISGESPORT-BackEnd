package com.ifg.sistema.sisgesport.api.entities;

import java.io.Serializable;

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
@Table(name="partida_ponto")
public class Partida_Ponto implements Serializable {

	private static final long serialVersionUID = -1618457060040789109L;

	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Long id;

	@ManyToOne
	@JoinColumn(name="jogador", referencedColumnName="id", nullable=false, foreignKey = @ForeignKey(name="fk_jogador_partida_ponto"))
	@NotNull(message="O campo jogador não pode ser nulo.")
	private Jogador jogador;
	
	@ManyToOne
	@JoinColumn(name="partida", referencedColumnName="id", nullable=false, foreignKey = @ForeignKey(name="fk_turma_partida_ponto"))
	@NotNull(message="O campo partida não pode ser nulo.")
	private Partida partida;
	
	@ManyToOne
	@JoinColumn(name="tipo_ponto", referencedColumnName="id", nullable=false, foreignKey = @ForeignKey(name="fk_tipo_ponto_partida_ponto"))
	private Tipo_Ponto tipoPonto;

	public Partida_Ponto() {	}

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
		return tipoPonto;
	}

	public void setTipo_ponto(Tipo_Ponto tipoPonto) {
		this.tipoPonto = tipoPonto;
	}
}
