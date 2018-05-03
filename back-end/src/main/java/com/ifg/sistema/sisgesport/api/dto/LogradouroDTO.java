package com.ifg.sistema.sisgesport.api.dto;

public class LogradouroDTO {
	private Long id;
	private String logradouro;
	private String ceplogradouro;
	private BairroDTO bairro;

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
		return ceplogradouro;
	}

	public void setCepLogradouro(String cepLogradouro) {
		this.ceplogradouro = cepLogradouro;
	}

	public BairroDTO getBairro() {
		return bairro;
	}

	public void setBairro(BairroDTO bairro) {
		this.bairro = bairro;
	}

}
