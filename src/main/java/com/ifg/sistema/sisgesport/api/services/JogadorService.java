package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ifg.sistema.sisgesport.api.entities.Jogador;

public interface JogadorService {
	/**
	 * Busca um jogador pelo id
	 * @param id
	 * @return Optional<Jogador>
	 */
	Optional<Jogador> BuscarId(Long id);
	/**
	 * Busca uma lista de jogadores pelo id do time
	 * @param id_time
	 * @return Optional<List<Jogador>>
	 */
	Optional<List<Jogador>> BuscarPorTimeId(Long id_time);
	/**
	 * Busca uma lista de jogadores pelo id da equipe
	 * @param id_equipe
	 * @return Optional<List<Jogador>>
	 */
	Optional<List<Jogador>> BuscarPorEquipeId(Long id_equipe);
	/**
	 * Busca uma lista paginada de jogadores pelo id do time
	 * @param id_time
	 * @param page
	 * @return Optional<Page<Jogador>>
	 */
	Optional<Page<Jogador>> BuscarPorTimeId(Long id_time, Pageable page);
	/**
	 * Busca uma lista paginada de jogadores pelo id da equipe
	 * @param id_equipe
	 * @param page
	 * @return Optional<Page<Jogador>>
	 */
	Optional<Page<Jogador>> BuscarPorEquipeId(Long id_equipe, Pageable page);
	/**
	 * Salva um novo jogador no banco de dados
	 * @param jogador
	 * @return Jogador
	 */
	Jogador Salvar(Jogador jogador);
}
