package com.ifg.sistema.sisgesport.api.services;

import java.util.Optional;

import com.ifg.sistema.sisgesport.api.entities.commom_entities.Pessoa;

public interface UsuarioService {
	/**
	 * Busca uma pessoa pela matricula
	 * 
	 * @param matricula
	 * @return Optional<Pessoa>
	 */
	Optional<Pessoa> BuscarPorMatricula(String matricula);
}
