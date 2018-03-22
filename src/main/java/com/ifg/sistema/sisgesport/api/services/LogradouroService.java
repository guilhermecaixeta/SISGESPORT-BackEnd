package com.ifg.sistema.sisgesport.api.services;

import java.util.Optional;

import com.ifg.sistema.sisgesport.api.entities.Logradouro;

public interface LogradouroService {
	/**
	 * Busca o logradouro pelo id
	 * @param id
	 * @return Optional<Logradouro>
	 */
	Optional<Logradouro> BuscarPorId(Long id);
	/**
	 * Busca o logradouro pelo cep logradouro
	 * @param cepLogradouro
	 * @return Optional<Logradouro>
	 */
	Optional<Logradouro> BuscarPorCepLogradouro(String cepLogradouro);
	/**
	 * Busca o logradouro pelo id do municipio ou cep do bairro ou cep logradouro
	 * @param cepmunicipio
	 * @param cepbairro
	 * @param ceplogradouro
	 * @return Optional<Logradouro>
	 */
	Optional<Logradouro> BuscarPorCepCompleto(String cepmunicipio, String cepbairro, String ceplogradouro);
	/**
	 * Salva um novo logradouro no banco de dados
	 * @param logradouro
	 * @return Logradouro
	 */
	Logradouro Salvar(Logradouro logradouro);
}
