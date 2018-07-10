package com.ifg.sistema.sisgesport.api.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="tipo_ponto")
public class TipoPonto implements Serializable {

	private static final long serialVersionUID = -6056988218785098576L;

	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Long id;
	
	@Column(name="nome", nullable=false, length=30)
	@NotNull(message="O campo nome não pode ser nulo.")
	@NotBlank(message="O campo nome não pode ser em branco.")
	@Length(max= 30,message="O campo nome possui o limite máximo de {max} caracteres.")
	private String nome;
	
	@Column(name="valor", nullable= false)
	@NotNull(message="O campo valor não pode ser nulo.")
	private int valor;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="modalidade_tipo_ponto", 
	joinColumns=
	@JoinColumn(name="tipo_ponto", referencedColumnName="id"),
	inverseJoinColumns =
	@JoinColumn(name="modalidade", referencedColumnName="id"),
	uniqueConstraints = {@UniqueConstraint(columnNames = {"modalidade", "tipo_ponto"})})
	private List<Modalidade> modalidade = new ArrayList<>();
	
	public TipoPonto() {	}

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

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public List<Modalidade> getModalidade() {
		return modalidade;
	}

	public void setModalidade(List<Modalidade> modalidade) {
		this.modalidade = modalidade;
	}
	
}
