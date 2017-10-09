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
@Table(name="cargo")
public class Cargo implements Serializable {

	private static final long serialVersionUID = 3L;
	
	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Long id;
	
	@Column(name="nome", nullable=false, unique= true, length=20)
	@NotNull(message="O campo nome não pode ser nulo.")
	@NotBlank(message="O campo nome não pode ser em branco.")
	@Length(max= 20,message="O campo nome possui o limite máximo de {max} caracteres.")
	private String nome;
	
	@Column(name="descricao", nullable=false, length=60)
	@NotNull(message="O campo descricao não pode ser nulo.")
	@NotBlank(message="O campo descricao não pode ser em branco.")
	@Length(max= 60,message="O campo descricao possui o limite máximo de {max} caracteres.")
	private String descricao;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="instituicao_cargo", 
	joinColumns=
	@JoinColumn(name="cargo", referencedColumnName="id", nullable= false),
	inverseJoinColumns =
	@JoinColumn(name="instituicao", referencedColumnName="id", nullable= false),
	uniqueConstraints = {@UniqueConstraint(columnNames = {"instituicao", "cargo"})})
	private List<Instituicao> instituicao = new ArrayList<>();
	
	public Cargo() {	}

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

	public List<Instituicao> getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(List<Instituicao> instituicao) {
		this.instituicao = instituicao;
	}
	
}
