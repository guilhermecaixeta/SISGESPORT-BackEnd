package com.ifg.sistema.sisgesport.api.entities;

import java.io.Serializable;

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
@Table(name="tipo_ponto")
public class Tipo_Ponto implements Serializable {

	private static final long serialVersionUID = 3L;
	
	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Integer id;
	
	@Column(name="nome", nullable=false, length=30)
	@NotNull(message="O campo nome não pode ser nulo.")
	@NotBlank(message="O campo nome não pode ser em branco.")
	@Length(max= 30,message="O campo nome possui o limite máximo de {max} caracteres.")
	private String nome;
	
	@Column(name="num_min_jogador", nullable= false, length= 1)
	@NotNull(message="O campo valor não pode ser nulo.")
	@NotBlank(message="O campo valor não pode ser em branco.")
	@Length(min= 1,message="O campo valor possui o limite minímo de {max} jogadores.")
	private int valor;
	
	@ManyToOne
	@JoinColumn(name="modalidade", referencedColumnName="id", nullable=false)
	@NotNull(message="O campo modalidade não pode ser nulo.")
	private Modalidade modalidade;

	public Tipo_Ponto() {	}

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

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public Modalidade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}
	
	
}
