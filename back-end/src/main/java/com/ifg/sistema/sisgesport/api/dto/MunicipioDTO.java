package com.ifg.sistema.sisgesport.api.dto;

import com.ifg.sistema.sisgesport.api.entities.Estado;

public class MunicipioDTO {
	private Long id;
	private String nome;
	private String sigla;
	private String cepMunicipio;
	private Estado estado;

	public MunicipioDTO() {
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getCepMunicipio() {
		return cepMunicipio;
	}

	public void setCepMunicipio(String cepMunicipio) {
		this.cepMunicipio = cepMunicipio;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
