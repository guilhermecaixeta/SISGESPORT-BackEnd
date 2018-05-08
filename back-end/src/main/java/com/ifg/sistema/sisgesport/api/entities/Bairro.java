package com.ifg.sistema.sisgesport.api.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
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
@Table(name = "bairro")
public class Bairro implements Serializable {

	private static final long serialVersionUID = 5155601236282601728L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nome", nullable = false, length = 30)
	@NotNull(message = "O campo nome não pode ser nulo.")
	@NotBlank(message = "O campo nome não pode ser em branco.")
	@Length(max = 30, message = "O campo nome possui o limite máximo de {max} caracteres.")
	private String nome;

	@Column(name = "cep_bairro", nullable = false, length = 3)
	private String cepBairro;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "municipio", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_municipio_bairro"))
	private Municipio municipio;

	public Bairro() {
	}

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

	public String getCepBairro() {
		return cepBairro;
	}

	public void setCepBairro(String cepBairro) {
		this.cepBairro = cepBairro;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

}
