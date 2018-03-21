package com.ifg.sistema.sisgesport.api.services;
import java.util.List;
import java.util.Optional;

import com.ifg.sistema.sisgesport.api.entities.Evento_Modalidade;

public interface EventoModalidadeService {
	/**
	 * Busca uma lista de eventos modalidades a partir do id do evento.
	 * @param id_evento
	 * @return Optional<List<Evento_Modalidade>>
	 */
	Optional<List<Evento_Modalidade>> BuscarPorEventoId(Long id_evento);
	/**
	 * Busca uma lista de eventos modalidades a partir do id da modalidade.
	 * @param id_modalidade
	 * @return Optional<List<Evento_Modalidade>>
	 */
	Optional<List<Evento_Modalidade>> BuscarPorModalidadeId(Long id_modalidade);
	/**
	 * Busca um evento modalidade a partir do id.
	 * @param id
	 * @return Optional<Evento_Modalidade>
	 */
	Optional<Evento_Modalidade> BuscarPorId(Long id);
	/**
	 * Salva um novo evento modalidade no banco de dados.
	 * @param evento_modalidade
	 * @return
	 */
	Evento_Modalidade Salvar(Evento_Modalidade evento_modalidade);
}
