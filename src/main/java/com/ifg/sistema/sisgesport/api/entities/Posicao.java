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
@Table(name="posicao")
public class Posicao implements Serializable {
	
	private static final long serialVersionUID = -5156128374225424613L;

	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Long id;
	
	@Column(name="nome", nullable=false, length=30)
	@NotNull(message="O campo nome não pode ser nulo.")
	@NotBlank(message="O campo nome não pode ser em branco.")
	@Length(max= 30,message="O campo nome possui o limite máximo de {max} caracteres.")
	private String nome;
	
	@Column(name="descricao", nullable=false, length=30)
	@NotNull(message="O campo descricao não pode ser nulo.")
	@NotBlank(message="O campo descricao não pode ser em branco.")
	@Length(max= 30,message="O campo descricao possui o limite máximo de {max} caracteres.")
	private String descricao;
	
	@Column(name="num_max_jogador", nullable= false)
	private int num_max_jogador;
	
	@Column(name="num_min_jogador", nullable= false)
	private int num_min_jogador;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="modalidade_posicao", 
	joinColumns=
	@JoinColumn(name="posicao", referencedColumnName="id", nullable= false),
	inverseJoinColumns =
	@JoinColumn(name="modalidade", referencedColumnName="id", nullable= false),
	uniqueConstraints = {@UniqueConstraint(columnNames = {"modalidade", "posicao"})})
	private List<Modalidade> modalidade = new ArrayList<>();

	public Posicao() {	}

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

	public int getNum_max_jogador() {
		return num_max_jogador;
	}

	public void setNum_max_jogador(int num_max_jogador) {
		this.num_max_jogador = num_max_jogador;
	}

	public int getNum_min_jogador() {
		return num_min_jogador;
	}

	public void setNum_min_jogador(int num_min_jogador) {
		this.num_min_jogador = num_min_jogador;
	}

	public List<Modalidade> getModalidade() {
		return modalidade;
	}

	public void setModalidade(List<Modalidade> modalidade) {
		this.modalidade = modalidade;
	}
	
}
