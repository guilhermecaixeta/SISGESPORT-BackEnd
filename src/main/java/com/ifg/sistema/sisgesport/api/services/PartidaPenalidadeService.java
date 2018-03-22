package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;

import com.ifg.sistema.sisgesport.api.entities.Partida_Penalidade;

public interface PartidaPenalidadeService {
	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<Partida_Penalidade> BuscarPorId(Long id);
	/**
	 * 
	 * @param id_jogador
	 * @return
	 */
	Optional<List<Partida_Penalidade>> BuscarPorJogadorId(Long id_jogador);
	/**
	 * 
	 * @param id_partida
	 * @return
	 */
	Optional<List<Partida_Penalidade>> BuscarPorPartidaId(Long id_partida);
	/**
	 * 
	 * @param id_penalidade
	 * @return
	 */
	Optional<List<Partida_Penalidade>> BuscarPorPenalidadeId(Long id_penalidade);
	/**
	 * 
	 * @param partida_penalidade
	 * @return
	 */
	Partida_Penalidade Salvar(Partida_Penalidade partida_penalidade);
}
