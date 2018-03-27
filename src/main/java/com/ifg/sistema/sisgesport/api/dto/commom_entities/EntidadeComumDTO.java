package com.ifg.sistema.sisgesport.api.dto.commom_entities;

import java.util.ArrayList;
import java.util.List;

import com.ifg.sistema.sisgesport.api.entities.Endereco;
import com.ifg.sistema.sisgesport.api.entities.Imagem;

public class EntidadeComumDTO {
	protected Long id;
	protected List<Endereco> endereco = new ArrayList<Endereco>();
	protected List<Imagem> imagem = new ArrayList<Imagem>();

	public EntidadeComumDTO() {	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	public List<Imagem> getImagem() {
		return imagem;
	}

	public void setImagem(List<Imagem> imagem) {
		this.imagem = imagem;
	}
}
