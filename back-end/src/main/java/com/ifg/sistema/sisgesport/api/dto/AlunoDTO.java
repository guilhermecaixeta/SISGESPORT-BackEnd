package com.ifg.sistema.sisgesport.api.dto;

import java.util.ArrayList;
import java.util.List;

import com.ifg.sistema.sisgesport.api.dto.commom_entities.PessoaDTO;
import com.ifg.sistema.sisgesport.api.entities.Equipe;

public class AlunoDTO extends PessoaDTO {

	private TurmaDTO turma;
	private List<Equipe> equipe = new ArrayList<>();

	public AlunoDTO() {
	}

	public TurmaDTO getTurma() {
		return turma;
	}

	public void setTurma(TurmaDTO turma) {
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
