package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ifg.sistema.sisgesport.api.entities.Curso;

public interface CursoService {
	
	/**
	 * Busca o curso pelo seu id
	 * @param id
	 * @return
	 */
	Optional<Curso> BuscarPorId(Long id);
	/**
	 * Busca o curso pelo id da instituicao
	 * @param id
	 * @return
	 */ 
	Optional<List<Curso>> BuscarEquipePorIdInstituicao(Long id_instituicao);
	/**
	 * Busca o curso pelo id da instituicao com paginacao
	 * @param id
	 * @param page
	 * @return
	 */
	Optional<Page<Curso>> BuscarEquipePorIdInstituicaoPaginavel(Long id_instituicao, Pageable page);
	/**
	 * Salva um novo curso no banco de dados
	 * @param curso
	 * @return
	 */
	Curso Salvar(Curso curso);
}
