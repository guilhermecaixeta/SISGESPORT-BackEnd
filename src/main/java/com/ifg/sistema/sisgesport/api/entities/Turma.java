package com.ifg.sistema.sisgesport.api.entities;
import java.io.Serializable;
import java.util.Calendar;

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
@Table(name="turma")
public class Turma  implements Serializable {

	private static final long serialVersionUID = 3L;
	
	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Integer id;
	
	@Column(name="flg_ativo", nullable=false)
	@NotNull(message="O campo não pode ser nulo.")
	private Boolean flg_ativo;
	
	@Column(name="nome", nullable=false, length=20)
	@NotNull(message="O campo nome não pode ser nulo.")
	@NotBlank(message="O campo nome não pode ser em branco.")
	@Length(max= 20,message="O campo nome possui o limite máximo de {max} caracteres.")
	private String nome;
	
	@Column(name="data_limite", nullable=false)
	private Calendar data_limite;
	
	@Column(name="data_inicial_turma", nullable=false)
	private Calendar data_inicial_turma;

	@ManyToOne
	@JoinColumn(name="curso", referencedColumnName="id")
	private Curso curso;

	public Turma() {	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Calendar getData_limite() {
		return data_limite;
	}

	public void setData_limite(Calendar data_limite) {
		this.data_limite = data_limite;
	}

	public Calendar getData_inicial_turma() {
		return data_inicial_turma;
	}

	public void setData_inicial_turma(Calendar data_inicial_turma) {
		this.data_inicial_turma = data_inicial_turma;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}
