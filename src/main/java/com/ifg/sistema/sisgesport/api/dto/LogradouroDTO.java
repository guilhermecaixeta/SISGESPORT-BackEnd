package com.ifg.sistema.sisgesport.api.dto;

import com.ifg.sistema.sisgesport.api.entities.Bairro;

public class LogradouroDTO {
	private Long id;
	private String logradouro;
	private String cepLogradouro;
	private Bairro bairro;

	public LogradouroDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCepLogradouro() {
		return cepLogradouro;
	}

	public void setCepLogradouro(String cepLogradouro) {
		this.cepLogradouro = cepLogradouro;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

}
