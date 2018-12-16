package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ifg.sistema.sisgesport.api.entities.Aluno;

public interface AlunoService {
	/**
	 * Busca o aluno pela matricula
	 * 
	 * @param matricula
	 * @return Optional<Aluno>
	 */
	Optional<Aluno> BuscarPorMatricula(String matricula);

	/**
	 * Busca um aluno pelo email
	 * 
	 * @param matricula
	 * @return
	 */
	Optional<Aluno> BuscarPorEmail(String email);

	/**
	 * Busca o aluno pelo id
	 * 
	 * @param id
	 * @return Optional<Aluno>
	 */
	Optional<Aluno> BuscarPorId(Long id);
	/**
	 * Busca o aluno pelo nome
	 * 
	 * @param nome
	 * @return Optional<Aluno>
	 */
	Optional<Aluno> BuscarPorNome(String nome);
	
	/**
	 * Busca os alunos pelo id da turma
	 * 
	 * @param id_turma
	 * @return Optional<Aluno>
	 */
	Optional<List<Aluno>> BuscarPorIdTurma(Long id_turma);

	/**
	 * Busca os alunos pelo id da turma com paginacao
	 * 
	 * @param id_turma
	 * @return Optional<Aluno>
	 */
	Page<Aluno> BuscarPorIdTurmaPaginavel(Long id_turma, PageRequest pageRequest);

	/**
	 * Busca os alunos pelo id da equipe
	 * 
	 * @param id_equipe
	 * @return Optional<Aluno>
	 */
	Optional<List<Aluno>> BuscarPorIdEquipe(Long id_equipe);

	/**
	 * Busca os alunos pelo id da equipe com paginacao
	 * 
	 * @param id_equipe
	 * @param pageRequest
	 * @return Page<Aluno>
	 */
	Page<Aluno> BuscarPorIdEquipePaginavel(Long id_equipe, PageRequest pageRequest);

	/**
	 * Salva um novo aluno no banco de dados
	 * 
	 * @param aluno
	 * @return Aluno
	 */
	Aluno Salvar(Aluno aluno);

	void Deletar(Long id);
}
