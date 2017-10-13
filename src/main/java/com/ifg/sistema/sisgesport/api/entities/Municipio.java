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
@Table(name="municipio")
public class Municipio implements Serializable {

	private static final long serialVersionUID = 3L;
	
	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Integer id;
	
	@Column(name="nome", nullable=false, unique= true, length=20)
	@NotNull(message="O campo nome não pode ser nulo.")
	@NotBlank(message="O campo nome não pode ser em branco.")
	@Length(max= 20,message="O campo nome possui o limite máximo de {max} caracteres.")
	private String nome;
	
	@NotNull(message="O campo sigla não pode ser nulo.")
	@NotBlank(message="O campo sigla não pode ser em branco.")
	@Length(max= 5,message="O sigla possui o limite máximo de {max} caracteres.")
	@Column(name="sigla", nullable=false, unique= true, length=5)
	private String sigla;
	
	@Column(name="cep_municipio", nullable=false, unique= true, length=2)
	private String cepmunicipio;
	
	@ManyToOne
	@JoinColumn(name="estado", referencedColumnName="id", nullable=false)
	@NotNull(message="O campo estado não pode ser nulo.")
	private Estado estado;

	public Municipio() {	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getCep_municipio() {
		return cepmunicipio;
	}

	public void setCep_municipio(String cepmunicipio) {
		this.cepmunicipio = cepmunicipio;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
}
