package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;

import com.ifg.sistema.sisgesport.api.entities.PartidaPonto;

public interface PartidaPontoService {
	/**
	 * Buscar uma partida ponto pelo id
	 * @param id
	 * @return
	 */
	Optional<PartidaPonto> BuscarPorId(Long id);
	/**
	 * Busca uma lista de partida ponto pelo id do jogador
	 * @param id_jogador
	 * @return
	 */
	Optional<List<PartidaPonto>> BuscarPorJogadorId(Long id_jogador);
	/**
	 * Busca uma lista de partida ponto pelo id da partida
	 * @param id_partida
	 * @return
	 */
	Optional<List<PartidaPonto>> BuscarPorPartidaId(Long id_partida);
	/**
	 * Busca uma lista de partida ponto pelo id da penalidade
	 * @param id_penalidade
	 * @return
	 */
	Optional<List<PartidaPonto>> BuscarPorTipoPontoId(Long id_tipo_ponto);
	/**
	 * Salva um novo ponto partiada no banco de dados
	 * @param partida_ponto
	 * @return
	 */
	PartidaPonto Salvar(PartidaPonto partida_ponto);
	void Deletar(Long id);
}
