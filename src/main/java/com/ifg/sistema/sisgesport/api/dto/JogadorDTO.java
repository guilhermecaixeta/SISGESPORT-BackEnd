package com.ifg.sistema.sisgesport.api.dto;

import java.util.ArrayList;
import java.util.List;

import com.ifg.sistema.sisgesport.api.entities.Aluno;
import com.ifg.sistema.sisgesport.api.entities.PartidaPenalidade;
import com.ifg.sistema.sisgesport.api.entities.PartidaPonto;
import com.ifg.sistema.sisgesport.api.entities.Posicao;
import com.ifg.sistema.sisgesport.api.entities.Time;

public class JogadorDTO {
	private Long id;
	private int numCamisa;
	private AlunoDTO jogador;
	private TimeDTO time;
	private PosicaoDTO posicao;
	private List<PartidaPenalidadeDTO> partidaPenalidade = new ArrayList<>();
	private List<PartidaPontoDTO> partidaPonto = new ArrayList<>();

	public JogadorDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumCamisa() {
		return numCamisa;
	}

	public void setNumCamisa(int numCamisa) {
		this.numCamisa = numCamisa;
	}

	public AlunoDTO getJogador() {
		return jogador;
	}

	public void setJogador(AlunoDTO jogador) {
		this.jogador = jogador;
	}

	public TimeDTO getTime() {
		return time;
	}

	public void setTime(TimeDTO time) {
		this.time = time;
	}

	public PosicaoDTO getPosicao() {
		return posicao;
	}

	public void setPosicao(PosicaoDTO posicao) {
		this.posicao = posicao;
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
