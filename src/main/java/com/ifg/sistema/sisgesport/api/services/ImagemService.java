package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;

import com.ifg.sistema.sisgesport.api.entities.Imagem;

public interface ImagemService {
	/**
	 * Busca uma lista de imagens a partir do id de uma entidade comum
	 * @param id_entidade_comum
	 * @return Optional<List<Imagem>>
	 */
	Optional<List<Imagem>> BuscarPorEntidadeComumId(Long id_entidade_comum);
	/**
	 * Busca uma lista de imagens a partir do id de uma informacao evento
	 * @param id_informacao_evento
	 * @return Optional<List<Imagem>>
	 */
	Optional<List<Imagem>> BuscarPorInformacaoEventoId(Long id_informacao_evento);
	/**
	 * Busca um imagem a partir do id 
	 * @param id
	 * @return
	 */
	Optional<Imagem> BuscarPorId(Long id);
	/**
	 * Salva uma nova imagem no banco de dados
	 * @param imagem
	 * @return
	 */
	Imagem Salvar(Imagem imagem);
}
