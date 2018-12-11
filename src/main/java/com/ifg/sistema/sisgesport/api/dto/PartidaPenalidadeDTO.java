package com.ifg.sistema.sisgesport.api.dto;

import com.ifg.sistema.sisgesport.api.entities.Jogador;
import com.ifg.sistema.sisgesport.api.entities.Partida;
import com.ifg.sistema.sisgesport.api.entities.Penalidade;

public class PartidaPenalidadeDTO {
	private Long id;
	private JogadorDTO jogador;
	private PartidaDTO  partida;
	private PenalidadeDTO  penalidade;

	public PartidaPenalidadeDTO() {
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

	public PenalidadeDTO  getPenalidade() {
		return penalidade;
	}

	public void setPenalidade(PenalidadeDTO  penalidade) {
		this.penalidade = penalidade;
	}

}
