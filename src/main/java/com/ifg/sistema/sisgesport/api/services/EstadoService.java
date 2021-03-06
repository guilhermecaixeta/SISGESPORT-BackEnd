package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;

import com.ifg.sistema.sisgesport.api.entities.Estado;

public interface EstadoService {
	
	/**
	 * Retorna uma lista contendo todos os estados cadastrados.
	 * @return
	 */
	Optional<List<Estado>> BuscarTodos();
	/**
	 * Retorna um estado de acordo com o nome ou UF do mesmo
	 * @param nomeOuUf
	 * @return Optional<List<Estado>>
	 */
	Optional<Estado> BuscarPorNomeOuUF(String nome, String UF);
	
	/**
	 * Retorna um estado de acordo com o id passado
	 * @param id
	 * @return Optional<List<Estado>>
	 */
	Optional<Estado> BuscarPorId(Long id);
	
	/**
	 * Salva um novo estado no banco de dados
	 * @param estado
	 * @return Estado
	 */
	Estado Salvar(Estado estado);
	void Deletar(Long id);
}
