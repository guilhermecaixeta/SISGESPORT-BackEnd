package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;

import com.ifg.sistema.sisgesport.api.entities.Partida_Ponto;

public interface PartidaPontoService {
	/**
	 * Buscar uma partida ponto pelo id
	 * @param id
	 * @return
	 */
	Optional<Partida_Ponto> BuscarPorId(Long id);
	/**
	 * Busca uma lista de partida ponto pelo id do jogador
	 * @param id_jogador
	 * @return
	 */
	Optional<List<Partida_Ponto>> BuscarPorJogadorId(Long id_jogador);
	/**
	 * Busca uma lista de partida ponto pelo id da partida
	 * @param id_partida
	 * @return
	 */
	Optional<List<Partida_Ponto>> BuscarPorPartidaId(Long id_partida);
	/**
	 * Busca uma lista de partida ponto pelo id da penalidade
	 * @param id_penalidade
	 * @return
	 */
	Optional<List<Partida_Ponto>> BuscarPorTipoPontoId(Long id_tipo_ponto);
	/**
	 * Salva um novo ponto partiada no banco de dados
	 * @param partida_ponto
	 * @return
	 */
	Partida_Ponto Salvar(Partida_Ponto partida_ponto);
	void Deletar(Long id);
}
