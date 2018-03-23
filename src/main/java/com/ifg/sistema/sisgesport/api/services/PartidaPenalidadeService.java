package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;

import com.ifg.sistema.sisgesport.api.entities.Partida_Penalidade;

public interface PartidaPenalidadeService {
	/**
	 * Busca uma partida penalidade pelo id
	 * @param id
	 * @return
	 */
	Optional<Partida_Penalidade> BuscarPorId(Long id);
	/**
	 * Busca uma lista de partida penalidade pelo id do jogador
	 * @param id_jogador
	 * @return
	 */
	Optional<List<Partida_Penalidade>> BuscarPorJogadorId(Long id_jogador);
	/**
	 * Busca uma lista de partida penalidade pelo id da partida
	 * @param id_partida
	 * @return
	 */
	Optional<List<Partida_Penalidade>> BuscarPorPartidaId(Long id_partida);
	/**
	 * Busca uma lista de partida penalidade pelo id da penalidade
	 * @param id_penalidade
	 * @return
	 */
	Optional<List<Partida_Penalidade>> BuscarPorPenalidadeId(Long id_penalidade);
	/**
	 * Salva uma nova partida no banco de dados
	 * @param partida_penalidade
	 * @return
	 */
	Partida_Penalidade Salvar(Partida_Penalidade partida_penalidade);
}
