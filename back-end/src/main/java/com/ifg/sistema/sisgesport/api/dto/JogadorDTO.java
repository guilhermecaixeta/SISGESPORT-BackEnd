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
	private Aluno jogador;
	private Time time;
	private Posicao posicao;
	private List<PartidaPenalidade> partidaPenalidade = new ArrayList<PartidaPenalidade>();
	private List<PartidaPonto> partidaPonto = new ArrayList<PartidaPonto>();

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

	public Aluno getJogador() {
		return jogador;
	}

	public void setJogador(Aluno jogador) {
		this.jogador = jogador;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Posicao getPosicao() {
		return posicao;
	}

	public void setPosicao(Posicao posicao) {
		this.posicao = posicao;
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
