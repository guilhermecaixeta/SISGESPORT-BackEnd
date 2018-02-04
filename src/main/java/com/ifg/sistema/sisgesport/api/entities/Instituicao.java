package com.ifg.sistema.sisgesport.api.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table ;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.ifg.sistema.sisgesport.api.entities.commom_entities.Entidade_Comum;

@Entity
@Table(name="instituicao")
public class Instituicao extends Entidade_Comum implements Serializable {

	private static final long serialVersionUID = 3L;
	
	@Column(name="descricao", nullable=false, length=200)
	@NotNull(message="O campo descricao não pode ser nulo.")
	@NotBlank(message="O campo descricao não pode ser em branco.")
	@Length(max= 200,message="O campo descricao possui o limite máximo de {max} caracteres.")
	private String descricao;
	
	@Column(name="nome", nullable=false, length=60)
	@NotNull(message="O campo nome não pode ser nulo.")
	@NotBlank(message="O campo nome não pode ser em branco.")
	@Length(max= 60,message="O campo nome possui o limite máximo de {max} caracteres.")
	private String nome;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="instituicao_cargo", 
	joinColumns=
	@JoinColumn(name="instituicao", referencedColumnName="id"),
	inverseJoinColumns =
	@JoinColumn(name="cargo", referencedColumnName="id"),
	uniqueConstraints = {@UniqueConstraint(columnNames = {"instituicao", "cargo"})})
	private List<Cargo> cargos = new ArrayList<Cargo>();
	
	public Instituicao() {	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}
	
}
