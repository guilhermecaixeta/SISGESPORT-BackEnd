package com.ifg.sistema.sisgesport.api.services;

import java.util.Optional;

import com.ifg.sistema.sisgesport.api.entities.Bairro;

public interface BairroService {

	/**
	 * Busca o bairro pelo cep
	 * @param cepBairro
	 * @return Optional<Bairro>
	 */
	Optional<Bairro> BuscarPorCepBairro(String cepBairro);
	/**
	 * Busca o bairro pelo nome
	 * @param nomeBairro
	 * @return Optional<Bairro>
	 */
	Optional<Bairro> BuscarPorNome(String nomeBairro);
	/**
	 * Busca o bairro pelo id
	 * @param id
	 * @return Optional<Bairro>
	 */
	Optional<Bairro> BuscarPorId(Long id);
	/**
	 * 
	 * @param bairro
	 * @return Bairro
	 */
	Bairro Salvar(Bairro bairro);
}
