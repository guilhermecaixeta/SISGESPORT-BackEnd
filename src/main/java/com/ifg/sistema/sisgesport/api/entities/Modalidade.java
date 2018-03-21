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
@Table(name="modalidade")
public class Modalidade  implements Serializable{

	private static final long serialVersionUID = 1594020995360031660L;

	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Long id;
	
	@Column(name="nome", nullable= false, length=30)
	@NotNull(message="O campo nome não pode ser nulo.")
	@NotBlank(message="O campo nome não pode ser em branco.")
	@Length(max= 30,message="O campo nome possui o limite máximo de {max} caracteres.")
	private String nome;
	
	@Column(name="descricao", length=400)
	@Length(max= 400,message="O descricao possui o limite máximo de {max} caracteres.")
	private String descricao;
	
	@Column(name="num_max_jogador" )
	private int numMaxJogador;
	
	@Column(name="num_min_jogador")
	private int numMinJogador;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="modalidade_penalidade", 
	joinColumns=
	@JoinColumn(name="modalidade", referencedColumnName="id"),
	inverseJoinColumns =
	@JoinColumn(name="penalidade", referencedColumnName="id"),
	uniqueConstraints = {@UniqueConstraint(columnNames = {"modalidade", "penalidade"})})
	private List<Penalidade> penalidade = new ArrayList<>();

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="modalidade_posicao", 
	joinColumns=
	@JoinColumn(name="modalidade", referencedColumnName="id"),
	inverseJoinColumns =
	@JoinColumn(name="posicao", referencedColumnName="id"),
	uniqueConstraints = {@UniqueConstraint(columnNames = {"modalidade", "posicao"})})
	private List<Posicao> posicao = new ArrayList<>();
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="modalidade_tipo_ponto", 
	joinColumns=
	@JoinColumn(name="modalidade", referencedColumnName="id"),
	inverseJoinColumns =
	@JoinColumn(name="tipo_ponto", referencedColumnName="id"),
	uniqueConstraints = {@UniqueConstraint(columnNames = {"modalidade", "tipo_ponto"})})
	private List<Tipo_Ponto> tipoPonto = new ArrayList<>();
	
	public Modalidade() {	}

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

	public int getNumMaxJogador() {
		return numMaxJogador;
	}

	public void setNumMaxJogador(int numMaxJogador) {
		this.numMaxJogador = numMaxJogador;
	}

	public int getNumMinJogador() {
		return numMinJogador;
	}

	public void setNumMinJogador(int numMinJogador) {
		this.numMinJogador = numMinJogador;
	}

	public List<Penalidade> getPenalidade() {
		return penalidade;
	}

	public void setPenalidade(List<Penalidade> penalidade) {
		this.penalidade = penalidade;
	}

	public List<Posicao> getPosicao() {
		return posicao;
	}

	public void setPosicao(List<Posicao> posicao) {
		this.posicao = posicao;
	}

	public List<Tipo_Ponto> getTipoPonto() {
		return tipoPonto;
	}

	public void setTipoPonto(List<Tipo_Ponto> tipoPonto) {
		this.tipoPonto = tipoPonto;
	}

}
