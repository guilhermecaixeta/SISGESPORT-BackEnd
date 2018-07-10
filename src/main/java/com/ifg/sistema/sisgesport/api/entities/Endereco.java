package com.ifg.sistema.sisgesport.api.entities;

import java.io.Serializable;

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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ifg.sistema.sisgesport.api.entities.commom_entities.EntidadeComum;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1782932071361353507L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "cep", nullable = false, length = 8)
	private String cep;

	@Column(name = "complemento", nullable = false, length = 255)
	@NotNull(message = "O campo complemento não pode ser nulo.")
	@NotBlank(message = "O campo complemento não pode ser em branco.")
	@Length(max = 255, message = "O campo complemento possui o limite máximo de {max} caracteres.")
	private String complemento;

	@Column(name = "logradouro", nullable = false, length = 255)
	@NotNull(message = "O campo logradouro não pode ser nulo.")
	@NotBlank(message = "O campo logradouro não pode ser em branco.")
	@Length(max = 255, message = "O campo logradouro possui o limite máximo de {max} caracteres.")
	private String logradouro;

	@Column(name = "bairro", nullable = false, length = 255)
	@NotNull(message = "O campo bairro não pode ser nulo.")
	@NotBlank(message = "O campo bairro não pode ser em branco.")
	@Length(max = 255, message = "O bairro nome possui o limite máximo de {max} caracteres.")
	private String bairro;

	@ManyToOne
	@JoinColumn(name = "municipio", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_municipio_endereco"))
	private Municipio municipio;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "entidade_comum", referencedColumnName = "id", nullable = true, foreignKey = @ForeignKey(name = "fk_entidade_comum_endereco"))
	private EntidadeComum entidadeComum;

	public Endereco() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public EntidadeComum getEntidadeComum() {
		return entidadeComum;
	}

	public void setEntidadeComum(EntidadeComum entidadeComum) {
		this.entidadeComum = entidadeComum;
	}
	
}
