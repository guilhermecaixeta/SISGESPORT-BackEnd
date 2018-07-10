package com.ifg.sistema.sisgesport.api.dto;

import java.util.ArrayList;
import java.util.List;

import com.ifg.sistema.sisgesport.api.entities.Modalidade;

public class PosicaoDTO {
	private Long id;
	private String nome;
	private String descricao;
	private int numMaxJogador;
	private int numMinJogador;
	private List<Modalidade> modalidade = new ArrayList<>();

	public PosicaoDTO() {
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

	public List<Modalidade> getModalidade() {
		return modalidade;
	}

	public void setModalidade(List<Modalidade> modalidade) {
		this.modalidade = modalidade;
	}

}
