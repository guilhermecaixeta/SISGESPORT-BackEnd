package com.ifg.sistema.sisgesport.api.services;

import java.util.Optional;

import com.ifg.sistema.sisgesport.api.entities.Modalidade;

public interface ModalidadeService {
	/**
	 * Busca a modalidade pelo id
	 * @param id
	 * @return Optional<Modalidade>
	 */
	Optional<Modalidade> BuscarPorId(Long id);
	/**
	 * Busca a modalidade pelo nome
	 * @param nome
	 * @return Optional<Modalidade>
	 */
	Optional<Modalidade> BuscarPorNome(String nome);
	/**
	 * Salva uma nova modalidade no banco de dados
	 * @param modalidade
	 * @return Modalidade
	 */
	Modalidade Salvar(Modalidade modalidade);
}
