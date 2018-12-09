package com.ifg.sistema.sisgesport.api.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ifg.sistema.sisgesport.api.entities.Imagem;

public class InformacaoEventoDTO {
	private Long id;
	private char tipoInformacao;
	private String titulo;
	private String descricao;
	private Date dataPostagem;
	private List<ImagemDTO> imagem = new ArrayList<ImagemDTO>();
	private EventoDTO evento;

	public InformacaoEventoDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public char getTipoInformacao() {
		return tipoInformacao;
	}

	public void setTipoInformacao(char tipoInformacao) {
		this.tipoInformacao = tipoInformacao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public List<ImagemDTO> getImagem() {
		return imagem;
	}

	public void setImagem(List<ImagemDTO> imagem) {
		this.imagem = imagem;
	}

	public EventoDTO getEvento() {
		return evento;
	}

	public void setEvento(EventoDTO evento) {
		this.evento = evento;
	}
}
