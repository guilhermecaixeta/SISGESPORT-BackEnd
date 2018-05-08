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
@Table(name="municipio")
public class Municipio implements Serializable {

	private static final long serialVersionUID = 6916088269478172145L;

	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Long id;

	@Column(name = "nome", nullable = false, unique = true, length = 45)
	@NotNull(message = "O campo nome não pode ser nulo.")
	@NotBlank(message = "O campo nome não pode ser em branco.")
	@Length(max = 45, message = "O campo nome possui o limite máximo de {max} caracteres.")
	private String nome;

	@NotNull(message = "O campo sigla não pode ser nulo.")
	@NotBlank(message = "O campo sigla não pode ser em branco.")
	@Length(max = 5, message = "O sigla possui o limite máximo de {max} caracteres.")
	@Column(name = "sigla", nullable = false, unique = true, length = 5)
	private String sigla;

	@Column(name = "cep_municipio", nullable = false, length = 2)
	private String cepMunicipio;

	@ManyToOne
	@JoinColumn(name = "estado", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_estado_municipio"))
	private Estado estado;

	public Municipio() {	}

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

	public String getSigla() {
		return sigla;
	}

	public String getCepMunicipio() {
		return cepMunicipio;
	}

	public void setCepMunicipio(String cepMunicipio) {
		this.cepMunicipio = cepMunicipio;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
}
