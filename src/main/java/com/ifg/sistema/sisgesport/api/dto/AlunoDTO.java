package com.ifg.sistema.sisgesport.api.dto;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.ifg.sistema.sisgesport.api.dto.commom_entities.PessoaDTO;
import com.ifg.sistema.sisgesport.api.entities.Equipe;

public class AlunoDTO extends PessoaDTO {
	@NotEmpty(message = "O campo matrícula não pode ser vazio.")
	@Length(message = "O campo matrícula deve conter até 20 caracteres.")
	private String matricula;
	@NotEmpty(message = "O campo turma não pode ser vazio.")
	private Long turma;
	private List<Equipe> equipe = new ArrayList<>();

	public AlunoDTO() {
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Long getTurma() {
		return turma;
	}

	public void setTurma(Long turma) {
		this.turma = turma;
	}

	public List<Equipe> getEquipe() {
		return equipe;
	}

	public void setEquipe(List<Equipe> equipe) {
		this.equipe = equipe;
	}

	@Override
	public String toString() {
		return "AlunoDTO[matricula " + matricula + ", nome " + nome + ", data Nascimento " + dataNascimento + "]";
	}
}
