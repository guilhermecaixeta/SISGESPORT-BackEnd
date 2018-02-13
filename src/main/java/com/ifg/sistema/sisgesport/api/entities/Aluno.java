package com.ifg.sistema.sisgesport.api.entities;
import java.io.Serializable ;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;


@Entity
public class Aluno extends Pessoa implements Serializable{
	
	private static final long serialVersionUID = 5195314769654228291L;

	@Column(name="matricula", nullable=false, unique= true, length=20)
	private String matricula;
	
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name="turma", referencedColumnName="id", nullable=false, foreignKey = @ForeignKey(name="fk_turma_aluno"))
	private Turma turma;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="equipe_aluno", 
	joinColumns=
	@JoinColumn(name="aluno", referencedColumnName="id"),
	inverseJoinColumns =
	@JoinColumn(name="equipe", referencedColumnName="id"),
	uniqueConstraints = {@UniqueConstraint(columnNames = {"equipe", "aluno"})})
	private List<Equipe> equipe = new ArrayList<>();
	
	public Aluno() {}

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

	public List<Equipe> getEquipe() {
		return equipe;
	}

	public void setEquipe(List<Equipe> equipe) {
		this.equipe = equipe;
	}

}
