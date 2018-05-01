package com.ifg.sistema.sisgesport.api.dto;

import com.ifg.sistema.sisgesport.api.entities.Logradouro;
import com.ifg.sistema.sisgesport.api.entities.commom_entities.EntidadeComum;

public class EnderecoDTO {
	private Long id;
	private String complemento;
	private Logradouro logradouro;
	private EntidadeComum entidadeComum;

	public EnderecoDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public EntidadeComum getEntidadeComum() {
		return entidadeComum;
	}

	public void setEntidadeComum(EntidadeComum entidadeComum) {
		this.entidadeComum = entidadeComum;
	}

}
