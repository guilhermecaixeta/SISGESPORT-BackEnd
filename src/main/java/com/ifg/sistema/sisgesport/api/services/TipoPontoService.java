package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ifg.sistema.sisgesport.api.entities.Tipo_Ponto;

public interface TipoPontoService {
	/**
	 * Busca um tipo ponto pelo id
	 * @param id
	 * @return Optional<Tipo_Ponto>
	 */
	Optional<Tipo_Ponto> BuscarPorId(Long id);
	/**
	 * Busca um tipo ponto pelo nome
	 * @param nome
	 * @return Optional<Tipo_Ponto>
	 */
	Optional<Tipo_Ponto> findByNome(String nome);
	/**
	 * Busca uma lista tipo ponto pelo id modalidade
	 * @param id
	 * @return Optional<List<Tipo_Ponto>>
	 */
	Optional<List<Tipo_Ponto>> findByModalidadeId(Long id_modalidade);
	/**
	 * Busca uma lista paginada tipo ponto pelo id modalidade
	 * @param id
	 * @param page
	 * @return Optional<Page<Tipo_Ponto>>
	 */ 
	Optional<Page<Tipo_Ponto>> findByModalidadeId(Long id_modalidade, Pageable page);
	/**
	 * Salva um tipo ponto no banco de dados
	 * @param tipo_ponto
	 * @return
	 */
	Tipo_Ponto Salvar(Tipo_Ponto tipo_ponto);
}
