package com.ifg.sistema.sisgesport.api.entities;

import java.io.Serializable;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.ForeignKey;
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
	private Integer id;
	
	@Column(name="logradouro", nullable=false, unique= true, length=45)
	@NotNull(message="O campo logradouro não pode ser nulo.")
	@NotBlank(message="O campo logradouro não pode ser em branco.")
	@Length(max= 45,message="O campo logradouro possui o limite máximo de {max} caracteres.")
	private String logradouro;
	
	@Column(name="cep_logradouro", nullable=false, unique= true, length=3)
	private String ceplogradouro;
	
	@ManyToOne
	@JoinColumn(name="bairro", referencedColumnName="id", nullable=false, foreignKey = @ForeignKey(name="fk_bairro_logradouro"))
	@NotNull(message="O campo bairro não pode ser nulo.")
	private Bairro bairro;

	public Logradouro() {	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep_logradouro() {
		return ceplogradouro;
	}

	public void setCep_logradouro(String ceplogradouro) {
		this.ceplogradouro = ceplogradouro;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
	
	
}
