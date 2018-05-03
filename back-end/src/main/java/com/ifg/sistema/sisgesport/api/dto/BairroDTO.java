package com.ifg.sistema.sisgesport.api.dto;

public class BairroDTO {
	private Long id;
	private String nome;
	private String cepbairro;
	private MunicipioDTO municipio;

	public BairroDTO() {
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

	public String getCepbairro() {
		return cepbairro;
	}

	public void setCepbairro(String cepbairro) {
		this.cepbairro = cepbairro;
	}

	public MunicipioDTO getMunicipio() {
		return municipio;
	}

	public void setMunicipio(MunicipioDTO municipio) {
		this.municipio = municipio;
	}

}
