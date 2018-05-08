package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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
	Page<Evento> BuscarPorMatriculaCriador(String matriculaSiap, PageRequest pageRequest);
	/**
	 * Busca pelo id do evento
	 * @param id
	 * @return
	 */
	Optional<Evento> BuscarPorCodigoEvento(String codigo_evento);
	/**
	 * Busca pelo codigo_evento do evento
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
	void Deletar(Long id);
}
