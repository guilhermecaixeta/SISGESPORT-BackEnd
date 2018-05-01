package com.ifg.sistema.sisgesport.api.services;
import java.util.List;
import java.util.Optional;

import com.ifg.sistema.sisgesport.api.entities.EventoModalidade;

public interface EventoModalidadeService {
	/**
	 * Busca uma lista de eventos modalidades a partir do id do evento.
	 * @param id_evento
	 * @return Optional<List<Evento_Modalidade>>
	 */
	Optional<List<EventoModalidade>> BuscarPorEventoId(Long id_evento);
	/**
	 * Busca uma lista de eventos modalidades a partir do id da modalidade.
	 * @param id_modalidade
	 * @return Optional<List<Evento_Modalidade>>
	 */
	Optional<List<EventoModalidade>> BuscarPorModalidadeId(Long id_modalidade);
	/**
	 * Busca um evento modalidade a partir do id.
	 * @param id
	 * @return Optional<Evento_Modalidade>
	 */
	Optional<EventoModalidade> BuscarPorId(Long id);
	/**
	 * Salva um novo evento modalidade no banco de dados.
	 * @param evento_modalidade
	 * @return
	 */
	EventoModalidade Salvar(EventoModalidade evento_modalidade);
	void Deletar(Long id);
}
