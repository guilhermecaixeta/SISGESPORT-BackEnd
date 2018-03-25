package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.ifg.sistema.sisgesport.api.entities.Informacao_Evento;

public interface InformacaoEventoService {
	/**
	 * Busca pelo codigo do evento uma lista de informacao eventos 
	 * @param codigo_evento
	 * @return Optional<List<Evento>>
	 */
	Optional<List<Informacao_Evento>> BuscarPorCodigoEvento(String codigo_evento);
	/**
	 * Busca pelo codigo do evento uma lista de informacao eventos paginada
	 * @param codigo_evento
	 * @param page
	 * @return Optional<Page<Evento>>
	 */
	Page<Informacao_Evento> BuscarPorCodigoEventoPaginavel(String codigo_evento, PageRequest pageRequest);
	/**
	 * Busca pelo id uma informacao eventos 
	 * @param id
	 * @return Optional<Evento>
	 */
	Optional<Informacao_Evento> BuscarPorId(Long id);
	/**
	 * Salva uma nova informacao evento
	 * @param evento
	 * @return Evento
	 */
	Informacao_Evento Salvar(Informacao_Evento evento);
	void Deletar(Long id);
}
