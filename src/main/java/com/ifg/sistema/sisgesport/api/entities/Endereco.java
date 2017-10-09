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
@Table(name="endereco")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 3L;
	
	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Long id;
	
	@Column(name="nome", nullable=false, unique= true, length=45)
	@NotNull(message="O campo complemento não pode ser nulo.")
	@NotBlank(message="O campo complemento não pode ser em branco.")
	@Length(max= 45,message="O campo complemento possui o limite máximo de {max} caracteres.")
	private String complemento;
	
	@ManyToOne
	@JoinColumn(name="logradouro", referencedColumnName="id", nullable=false)
	@NotNull(message="O campo logradouro não pode ser nulo.")
	private Logradouro logradouro;

	public Endereco() {	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}
	
}
