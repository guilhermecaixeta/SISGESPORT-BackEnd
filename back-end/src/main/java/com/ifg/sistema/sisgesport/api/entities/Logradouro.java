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
@Table(name = "logradouro")
public class Logradouro implements Serializable {

	private static final long serialVersionUID = -3368360483031988758L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "logradouro", nullable = false, length = 60)
	@NotNull(message = "O campo logradouro não pode ser nulo.")
	@NotBlank(message = "O campo logradouro não pode ser em branco.")
	@Length(max = 60, message = "O campo logradouro possui o limite máximo de {max} caracteres.")
	private String logradouro;

	@Column(name = "cep_logradouro", nullable = false, length = 3)
	private String cepLogradouro;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "bairro", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_bairro_logradouro"))
	@NotNull(message = "O campo bairro não pode ser nulo.")
	private Bairro bairro;

	public Logradouro() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCepLogradouro() {
		return cepLogradouro;
	}

	public void setCepLogradouro(String cepLogradouro) {
		this.cepLogradouro = cepLogradouro;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

}
