package com.ifg.sistema.sisgesport.api.entities;

import java.io.Serializable;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id ;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table ;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="logradouro")
public class Logradouro implements Serializable {

	private static final long serialVersionUID = 3L;
	
	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Long id;
	
	@Column(name="nome", nullable=false, unique= true, length=30)
	@NotNull(message="O campo logradouro não pode ser nulo.")
	@NotBlank(message="O campo logradouro não pode ser em branco.")
	@Length(max= 30,message="O campo logradouro possui o limite máximo de {max} caracteres.")
	private String descricao;
	
	@Column(name="cep_logradouro", nullable=false, unique= true, length=3)
	private String cep_logradouro;
	
	@ManyToOne
	@JoinColumn(name="bairro", referencedColumnName="id", nullable=false)
	@NotNull(message="O campo bairro não pode ser nulo.")
	private Bairro bairro;

	public Logradouro() {	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCep_logradouro() {
		return cep_logradouro;
	}

	public void setCep_logradouro(String cep_logradouro) {
		this.cep_logradouro = cep_logradouro;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
	
	
}
