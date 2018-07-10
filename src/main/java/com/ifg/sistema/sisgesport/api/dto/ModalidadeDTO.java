package com.ifg.sistema.sisgesport.api.dto;

import java.util.ArrayList;
import java.util.List;

import com.ifg.sistema.sisgesport.api.entities.Penalidade;
import com.ifg.sistema.sisgesport.api.entities.Posicao;
import com.ifg.sistema.sisgesport.api.entities.TipoPonto;

public class ModalidadeDTO {
	private Long id;
	private String nome;
	private String descricao;
	private int numMaxJogador;
	private int numMinJogador;
	private List<Penalidade> penalidade = new ArrayList<>();
	private List<Posicao> posicao = new ArrayList<>();
	private List<TipoPonto> tipoPonto = new ArrayList<>();

	public ModalidadeDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getNumMaxJogador() {
		return numMaxJogador;
	}

	public void setNumMaxJogador(int numMaxJogador) {
		this.numMaxJogador = numMaxJogador;
	}

	public int getNumMinJogador() {
		return numMinJogador;
	}

	public void setNumMinJogador(int numMinJogador) {
		this.numMinJogador = numMinJogador;
	}

	public List<Penalidade> getPenalidade() {
		return penalidade;
	}

	public void setPenalidade(List<Penalidade> penalidade) {
		this.penalidade = penalidade;
	}

	public List<Posicao> getPosicao() {
		return posicao;
	}

	public void setPosicao(List<Posicao> posicao) {
		this.posicao = posicao;
	}

	public List<TipoPonto> getTipoPonto() {
		return tipoPonto;
	}

	public void setTipoPonto(List<TipoPonto> tipoPonto) {
		this.tipoPonto = tipoPonto;
	}

}
