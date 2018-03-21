package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ifg.sistema.sisgesport.api.entities.Evento;

public interface EventoService {
	/**
	 * Busca pela matricula do criador do evento
	 * @param matricula
	 * @return Optional<List<Evento>>
	 */
	Optional<List<Evento>> BuscarPorMatriculaCriador(String matriculaSiap);
	/**
	 * Busca pela matricula do criador do evento paginada
	 * @param matricula
	 * @param page
	 * @return
	 */
	Optional<Page<Evento>> BuscarPorMatriculaCriador(String matriculaSiap, Pageable page);
	/**
	 * Busca pelo id do evento
	 * @param id
	 * @return
	 */
	Optional<Evento> BuscarPorId(Long id);
	/**
	 * Salva um novo evento no banco de dados
	 * @param evento
	 * @return
	 */
	Evento Salvar(Evento evento);
}
