package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ifg.sistema.sisgesport.api.entities.Turma;

public interface TurmaService {
	/**
	 * Busca uma turma pelo id
	 * @param id
	 * @return Optional<Tipo_Ponto>
	 */
	Optional<Turma> BuscarPorId(Long id);
	/**
	 *  Busca uma turma pelo nome
	 * @param nome
	 * @return
	 */
	Optional<Turma> BuscarPorNome(String nome);
	/**
	 *  Busca uma lista de turmas pelo id curso
	 * @param id_curso
	 * @return
	 */
	Optional<List<Turma>> BuscarPorCursoId(Long id_curso);
	/**
	 *  Busca uma lista paginada de turmas pelo id curso
	 * @param id_curso
	 * @return
	 */
	Page<Turma> BuscarPorCursoIdPaginavel(Long id_curso, PageRequest pageRequest);
	/**
	 * Salva uma nova turma no banco de dados
	 * @param turma
	 * @return
	 */
	Turma Salvar(Turma turma);
	void Deletar(Long id);
}
