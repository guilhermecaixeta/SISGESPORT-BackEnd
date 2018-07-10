package com.ifg.sistema.sisgesport.api.dto.commom_entities;

import java.util.ArrayList;
import java.util.List;

import com.ifg.sistema.sisgesport.api.dto.EnderecoDTO;
import com.ifg.sistema.sisgesport.api.dto.ImagemDTO;

public class EntidadeComumDTO {
	protected Long id;
	protected List<EnderecoDTO> endereco = new ArrayList<EnderecoDTO>();
	protected List<ImagemDTO> imagem = new ArrayList<ImagemDTO>();

	public EntidadeComumDTO() {	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<EnderecoDTO> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<EnderecoDTO> endereco) {
		this.endereco = endereco;
	}

	public List<ImagemDTO> getImagem() {
		return imagem;
	}

	public void setImagem(List<ImagemDTO> imagem) {
		this.imagem = imagem;
	}
}
