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

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="evento")
public class Evento implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Integer id;
	
	@Column(name="nome" ,nullable = false, length=30)
	@NotNull(message="O campo nome não pode ser nulo.")
	@NotBlank(message="O campo nome não pode ser em branco.")
	@Length(max= 30,message="O campo possui o limite máximo de {max} caracteres.")
	private String nome;
	
	@Column(name="descricao" ,nullable = false, length=400)
	@NotNull(message="O campo descricao não pode ser nulo.")
	@NotBlank(message="O campo descricao não pode ser em branco.")
	@Length(max= 400,message="O descricao possui o limite máximo de {max} caracteres.")
	private String descricao;
	
	@Column(name="data_inicio" ,nullable = false)
	private Calendar data_inicio;
	
	@Column(name="data_fim" ,nullable = false)
	private Calendar data_fim;
	
	@ManyToOne
	@JoinColumn(name="endereco", referencedColumnName="id", nullable=false)
	@NotNull(message="O campo endereco não pode ser nulo.")
	private Endereco endereco;
	
	@ManyToOne
	@JoinColumn(name="servidor", referencedColumnName="id", nullable=false)
	@NotNull(message="O campo criador não pode ser nulo.")
	private Servidor criador;
	
	@ManyToOne
	@JoinColumn(name="imagem", referencedColumnName="id", nullable=false)
	@NotNull(message="O imagem criador não pode ser nulo.")
	private Imagem imagem;
	
	public Evento() {	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Calendar getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(Calendar data_inicio) {
		this.data_inicio = data_inicio;
	}

	public Calendar getData_fim() {
		return data_fim;
	}

	public void setData_fim(Calendar data_fim) {
		this.data_fim = data_fim;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Servidor getCriador() {
		return criador;
	}

	public void setCriador(Servidor criador) {
		this.criador = criador;
	}

	public Imagem getImagem() {
		return imagem;
	}

	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}
	
}
