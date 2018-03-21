package com.ifg.sistema.sisgesport.api.entities;
import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.ForeignKey;
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
	
	private static final long serialVersionUID = -6358838271284684107L;

	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Long id;
	
	@Column(name="flg_ativo", nullable=false)
	@NotNull(message="O campo não pode ser nulo.")
	private Boolean flgAtivo;
	
	@Column(name="nome", nullable=false, length=20)
	@NotNull(message="O campo nome não pode ser nulo.")
	@NotBlank(message="O campo nome não pode ser em branco.")
	@Length(max= 20,message="O campo nome possui o limite máximo de {max} caracteres.")
	private String nome;
	
	@Column(name="data_limite", nullable=false)
	private Calendar dataLimite;
	
	@Column(name="data_inicial_turma", nullable=false)
	private Calendar dataInicialTurma;

	@ManyToOne
	@JoinColumn(name="curso", referencedColumnName="id", foreignKey = @ForeignKey(name="fk_curso_turma"))
	private Curso curso;

	public Turma() {	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getFlgAtivo() {
		return flgAtivo;
	}

	public void setFlgAtivo(Boolean flgAtivo) {
		this.flgAtivo = flgAtivo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(Calendar dataLimite) {
		this.dataLimite = dataLimite;
	}

	public Calendar getDataInicialTurma() {
		return dataInicialTurma;
	}

	public void setDataInicialTurma(Calendar dataInicialTurma) {
		this.dataInicialTurma = dataInicialTurma;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}
