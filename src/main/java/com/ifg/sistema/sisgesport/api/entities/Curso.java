package com.ifg.sistema.sisgesport.api.entities;
import java.io.Serializable;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GeneratedValue ;
import javax.persistence.GenerationType ;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table ;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="curso")
public class Curso implements Serializable {

	private static final long serialVersionUID = 3L;
	
	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Long id;
	
	@Column(name="flg_ativo", nullable=false)
	@NotNull(message="O campo não pode ser nulo.")
	private Boolean flg_ativo;
	
	@Column(name="nome", nullable=false, unique= true, length=20)
	@NotNull(message="O campo nome não pode ser nulo.")
	@NotBlank(message="O campo nome não pode ser em branco.")
	@Length(max= 20,message="O campo nome possui o limite máximo de {max} caracteres.")
	private String nome;

	@ManyToOne
	@JoinColumn(name="instituicao", referencedColumnName="id", nullable=false)
	@NotNull(message="O campo instituição não pode ser nulo.")
	private Instituicao instituicao;

	public Curso() {	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getFlg_ativo() {
		return flg_ativo;
	}

	public void setFlg_ativo(Boolean flg_ativo) {
		this.flg_ativo = flg_ativo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	
}
