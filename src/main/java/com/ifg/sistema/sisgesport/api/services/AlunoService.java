package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ifg.sistema.sisgesport.api.entities.Aluno;

public interface AlunoService {
	/**
	 * Realiza uma busca por matricula do aluno
	 */
	Optional<Aluno> BuscarPorMatricula(String matricula);
	
	Optional<List<Aluno>> BuscarPorIdTurma(Integer id);
	
	Optional<Page<Aluno>> BuscarPorIdTurmaPagination(Integer id, Pageable pageable);
	
	Optional<List<Aluno>> BuscarPorIdEquipe(Integer id);
	
	Optional<Page<Aluno>> BuscarPorIdEquipePagination(Integer id, Pageable pageable);
	
	/**
	 * Cadastra um novo aluno no banco de dados
	 */
	Aluno Salvar(Aluno aluno);
	
	/*
	 * Deleta um aluno
	 */
	void Deletar(Aluno aluno);
}
