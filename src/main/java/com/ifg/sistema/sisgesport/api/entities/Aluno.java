package com.ifg.sistema.sisgesport.api.entities;
import java.io.Serializable ;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
public class Aluno extends Pessoa implements Serializable{
	private static final long serialVersionUID = 3L;
	
	@Id
	@GeneratedValue ( strategy = GenerationType . AUTO )
	private Long id;
	
	@Column(name="matricula", nullable=false, unique= true, length=20)
	private String matricula;
	
	@ManyToOne
	@JoinColumn(name="turma", referencedColumnName="id", nullable=false)
	@NotNull(message="O campo turma não pode ser nulo.")
	private Turma turma;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="equipe_aluno", 
	joinColumns=
	@JoinColumn(name="aluno", referencedColumnName="id", nullable= false),
	inverseJoinColumns =
	@JoinColumn(name="equipe", referencedColumnName="id", nullable= false),
	uniqueConstraints = {@UniqueConstraint(columnNames = {"equipe", "aluno"})})
	private List<Equipe> equipes = new ArrayList<>();
	
	public Aluno() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Equipe> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}

}
