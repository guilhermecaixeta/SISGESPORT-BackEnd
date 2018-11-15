package com.ifg.sistema.sisgesport.api.dto;

import java.util.ArrayList;
import java.util.List;

import com.ifg.sistema.sisgesport.api.entities.Instituicao;

public class CargoDTO {
	private Long id;
	private String nome;
	private String descricao;
	private List<InstituicaoDTO> instituicao = new ArrayList<>();

	public CargoDTO() {
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

	public List<InstituicaoDTO> getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(List<InstituicaoDTO> instituicao) {
		this.instituicao = instituicao;
	}

}
