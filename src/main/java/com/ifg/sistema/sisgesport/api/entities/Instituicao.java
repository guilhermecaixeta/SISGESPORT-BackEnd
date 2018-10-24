package com.ifg.sistema.sisgesport.api.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ifg.sistema.sisgesport.api.entities.commom_entities.EntidadeComum;

@Entity
@Table(name = "instituicao")
public class Instituicao extends EntidadeComum implements Serializable {

	private static final long serialVersionUID = -5775475003607607110L;

	@Column(name = "descricao", nullable = false, length = 255)
	@NotNull(message = "O campo descricao não pode ser nulo.")
	@NotBlank(message = "O campo descricao não pode ser em branco.")
	@Length(max = 255, message = "O campo descricao possui o limite máximo de {max} caracteres.")
	private String descricao;

	@Column(name = "nome", nullable = false, length = 255)
	@NotNull(message = "O campo nome não pode ser nulo.")
	@NotBlank(message = "O campo nome não pode ser em branco.")
	@Length(max = 255, message = "O campo nome possui o limite máximo de {max} caracteres.")
	private String nome;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "instituicao_cargo", joinColumns = @JoinColumn(name = "instituicao", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "cargo", referencedColumnName = "id"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "instituicao", "cargo" }) })
	private List<Cargo> cargos = new ArrayList<Cargo>();

	public Instituicao() {
	}

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
