package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ifg.sistema.sisgesport.api.entities.Municipio;

public interface MunicipioService {
	/**
	 * Busca o municipio pelo id
	 * @param id
	 * @return Optional<Municipio>
	 */
	Optional<Municipio> BuscarPorId(Long id);
	/**
	 * Busca o municipio pelo nome ou sigla
	 * @param nome
	 * @param sigla
	 * @return Optional<Municipio>
	 */
	Optional<Municipio> BuscarPorNome(String nome);
	/**
	 * Busca uma lista de municipios pelo id do estado
	 * @param id_estado
	 * @return
	 */
	Optional<List<Municipio>> BuscarPorEstadoId(Long id_estado);
	/**
	 * Busca uma lista paginada de municipios pelo id do estado
	 * @param id_estado
	 * @param page
	 * @return
	 */
	Page<Municipio> BuscarPorEstadoIdPaginavel(Long id_estado, PageRequest pageRequest);
	/**
	 * Salva um novo municipio no banco de dados
	 * @param municipio
	 * @return Municipio
	 */
	Municipio Salvar(Municipio municipio);
	void Deletar(Long id);
}
