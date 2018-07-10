package com.ifg.sistema.sisgesport.api.dto;

import com.ifg.sistema.sisgesport.api.entities.Jogador;
import com.ifg.sistema.sisgesport.api.entities.Partida;
import com.ifg.sistema.sisgesport.api.entities.TipoPonto;

public class PartidaPontoDTO {
	private Long id;
	private Jogador jogador;
	private Partida partida;
	private TipoPonto tipoPonto;

	public PartidaPontoDTO() {
	}

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

	public TipoPonto getTipoPonto() {
		return tipoPonto;
	}

	public void setTipoPonto(TipoPonto tipoPonto) {
		this.tipoPonto = tipoPonto;
	}

}
