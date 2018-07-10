package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ifg.sistema.sisgesport.api.entities.Partida;

public interface PartidaService {
	/**
	 * Busca uma partida pelo id
	 * @param id
	 * @return
	 */
	Optional<Partida> BuscarPorId(Long id);
	/**
	 * Busca uma lista de partidas pelo id do time da casa 
	 * @param id_time_casa
	 * @return
	 */
	Optional<List<Partida>> BuscarPorTimeCasaId(Long id_time_casa);
	/**
	 * Busca uma lista de partidas pelo id do time paginada
	 * @param id_time_casa
	 * @param page
	 * @return
	 */
	Page<Partida> BuscarPorTimeCasaIdPaginavel(Long id_time_casa, PageRequest pageRequest);
	/**
	 * Busca uma lista de partidas pelo id do evento 
	 * @param id_evento
	 * @return
	 */
	Optional<List<Partida>> BuscarPorEventoId(Long id_evento);
	/**
	 * Busca uma lista de partidas pelo id do evento paginada
	 * @param id_evento
	 * @param page
	 * @return
	 */
	Page<Partida> BuscarPorEventoIdPaginavel(Long id_evento, PageRequest pageRequest);
	/**
	 * Salva uma nova partida no banco de dados
	 * @param partida
	 * @return
	 */
	Partida Salvar(Partida partida);
	void Deletar(Long id);
}
