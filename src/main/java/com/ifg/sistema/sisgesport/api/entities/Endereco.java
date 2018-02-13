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

import com.ifg.sistema.sisgesport.api.entities.commom_entities.Entidade_Comum;

@Entity
@Table(name="endereco")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1782932071361353507L;

	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Integer id;
	
	@Column(name="complemento", nullable=false, unique= true, length=60)
	@NotNull(message="O campo complemento não pode ser nulo.")
	@NotBlank(message="O campo complemento não pode ser em branco.")
	@Length(max= 60,message="O campo complemento possui o limite máximo de {max} caracteres.")
	private String complemento;
	
	@ManyToOne
	@JoinColumn(name="logradouro", referencedColumnName="id", nullable=false, foreignKey = @ForeignKey(name="fk_logradouro_endereco"))
	@NotNull(message="O campo logradouro não pode ser nulo.")
	private Logradouro logradouro;
	
	@ManyToOne
	@JoinColumn(name="entidade_comum", referencedColumnName="id", nullable=true, foreignKey = @ForeignKey(name="fk_entidade_comum_endereco"))
	private Entidade_Comum entidade_comum;

	public Endereco() {	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Entidade_Comum getEntidade_comum() {
		return entidade_comum;
	}

	public void setObjeto_comum(Entidade_Comum entidade_comum) {
		this.entidade_comum = entidade_comum;
	}
	
}
