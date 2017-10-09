package com.ifg.sistema.sisgesport.api.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="informacao_evento")
public class Informacao_Evento implements Serializable {

	private static final long serialVersionUID = 3L;
	
	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Long id;
	
	@Column(name="", nullable=false)
	private char tipo_informacao;
	
	@Column(name="", nullable=false, length= 35)
	private String titulo;
	
	@Column(name="", nullable=false, length= 400)
	private String descricao;
	
	@Column(name="data_postagem")
	private Calendar data_postagem;
	
	@ManyToOne
	@JoinColumn(name="imagem", referencedColumnName="id", nullable=false)
	@NotNull(message="O campo imagem não pode ser nulo.")
	private Imagem imagem;
	
	@ManyToOne
	@JoinColumn(name="evento", referencedColumnName="id", nullable=false)
	@NotNull(message="O campo evento não pode ser nulo.")
	private Evento evento;

	public Informacao_Evento() {	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public char getTipo_informacao() {
		return tipo_informacao;
	}

	public void setTipo_informacao(char tipo_informacao) {
		this.tipo_informacao = tipo_informacao;
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

	public Calendar getData_postagem() {
		return data_postagem;
	}

	public void setData_postagem(Calendar data_postagem) {
		this.data_postagem = data_postagem;
	}

	public Imagem getImagem() {
		return imagem;
	}

	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
}
