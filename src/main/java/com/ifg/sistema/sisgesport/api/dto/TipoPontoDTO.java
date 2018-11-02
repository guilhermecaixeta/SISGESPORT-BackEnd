package com.ifg.sistema.sisgesport.api.dto;

import java.util.ArrayList;
import java.util.List;

import com.ifg.sistema.sisgesport.api.entities.Modalidade;

public class TipoPontoDTO {
	private Long id;
	private String nome;
	private int valor;
//	private List<Modalidade> modalidade = new ArrayList<>();

	public TipoPontoDTO() {
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

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
//
//	public List<Modalidade> getModalidade() {
//		return modalidade;
//	}
//
//	public void setModalidade(List<Modalidade> modalidade) {
//		this.modalidade = modalidade;
//	}

}
