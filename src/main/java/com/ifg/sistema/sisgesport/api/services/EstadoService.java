package com.ifg.sistema.sisgesport.api.services;

import java.util.Optional;

import com.ifg.sistema.sisgesport.api.entities.Estado;

public interface EstadoService {
	
	/**
	 * Retorna um estado de acordo com o nome ou UF do mesmo
	 * @param nomeOuUf
	 * @return Optional<List<Estado>>
	 */
	Optional<Estado> BuscarPorNomeOuUF(String nome, String UF);
	
	/**
	 * Retorna um estado de acordo com o id passado
	 * @param nomeOuUf
	 * @return Optional<List<Estado>>
	 */
	Optional<Estado> BuscarPorId(Long id);
	
	/**
	 * Salva um novo estado no banco de dados
	 * @param estado
	 * @return Estado
	 */
	Estado Salvar(Estado estado);
}
