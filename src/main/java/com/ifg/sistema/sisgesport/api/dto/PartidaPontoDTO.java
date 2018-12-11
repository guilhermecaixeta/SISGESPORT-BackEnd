package com.ifg.sistema.sisgesport.api.dto;

import com.ifg.sistema.sisgesport.api.entities.Jogador;
import com.ifg.sistema.sisgesport.api.entities.Partida;
import com.ifg.sistema.sisgesport.api.entities.TipoPonto;

public class PartidaPontoDTO {
	private Long id;
	private JogadorDTO  jogador;
	private PartidaDTO  partida;
	private TipoPontoDTO  tipoPonto;

	public PartidaPontoDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public JogadorDTO  getJogador() {
		return jogador;
	}

	public void setJogador(JogadorDTO  jogador) {
		this.jogador = jogador;
	}

	public PartidaDTO  getPartida() {
		return partida;
	}

	public void setPartida(PartidaDTO  partida) {
		this.partida = partida;
	}

	public TipoPontoDTO  getTipoPonto() {
		return tipoPonto;
	}

	public void setTipoPonto(TipoPontoDTO  tipoPonto) {
		this.tipoPonto = tipoPonto;
	}

}
