package com.ifg.sistema.sisgesport.api.dto;

import java.util.ArrayList;
import java.util.List;

import com.ifg.sistema.sisgesport.api.dto.commom_entities.EntidadeComumDTO;
import com.ifg.sistema.sisgesport.api.entities.Cargo;

public class InstituicaoDTO extends EntidadeComumDTO {
	private String descricao;
	private String nome;

	public InstituicaoDTO() {
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
