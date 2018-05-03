package com.ifg.sistema.sisgesport.api.dto;

public class MunicipioDTO {
	private Long id;
	private String nome;
	private String sigla;
	private String cepmunicipio;
	private EstadoDTO estado;

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
		return cepmunicipio;
	}

	public void setCepMunicipio(String cepmunicipio) {
		this.cepmunicipio = cepmunicipio;
	}

	public EstadoDTO getEstado() {
		return estado;
	}

	public void setEstado(EstadoDTO estado) {
		this.estado = estado;
	}

}
