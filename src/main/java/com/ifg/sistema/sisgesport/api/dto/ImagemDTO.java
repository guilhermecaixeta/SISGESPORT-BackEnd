package com.ifg.sistema.sisgesport.api.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ifg.sistema.sisgesport.api.entities.InformacaoEvento;
import com.ifg.sistema.sisgesport.api.entities.commom_entities.EntidadeComum;

public class ImagemDTO {
	private Long id;
	private byte[] imagem;
	private String descricaoImagem;
	private String nome;
	private Double tamanho;
	private Date dataImagem;
	private EntidadeComum entidadeComum;
	private List<InformacaoEvento> informacaoEvento = new ArrayList<InformacaoEvento>();

	public ImagemDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public String getDescricaoImagem() {
		return descricaoImagem;
	}

	public void setDescricaoImagem(String descricaoImagem) {
		this.descricaoImagem = descricaoImagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getTamanho() {
		return tamanho;
	}

	public void setTamanho(Double tamanho) {
		this.tamanho = tamanho;
	}

	public Date getDataImagem() {
		return dataImagem;
	}

	public void setDataImagem(Date dataImagem) {
		this.dataImagem = dataImagem;
	}

	public EntidadeComum getEntidadeComum() {
		return entidadeComum;
	}

	public void setEntidadeComum(EntidadeComum entidadeComum) {
		this.entidadeComum = entidadeComum;
	}

	public List<InformacaoEvento> getInformacaoEvento() {
		return informacaoEvento;
	}

	public void setInformacaoEvento(List<InformacaoEvento> informacaoEvento) {
		this.informacaoEvento = informacaoEvento;
	}

}
