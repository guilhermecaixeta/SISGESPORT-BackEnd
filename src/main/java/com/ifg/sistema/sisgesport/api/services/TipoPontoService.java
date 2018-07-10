package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.ifg.sistema.sisgesport.api.entities.TipoPonto;

public interface TipoPontoService {
	/**
	 * Busca um tipo ponto pelo id
	 * @param id
	 * @return Optional<Tipo_Ponto>
	 */
	Optional<TipoPonto> BuscarPorId(Long id);
	/**
	 * Busca um tipo ponto pelo nome
	 * @param nome
	 * @return Optional<Tipo_Ponto>
	 */
	Optional<TipoPonto> BuscarPorNome(String nome);
	/**
	 * Busca uma lista tipo ponto pelo id modalidade
	 * @param id
	 * @return Optional<List<Tipo_Ponto>>
	 */
	Optional<List<TipoPonto>> BuscarPorModalidadeId(Long id_modalidade);
	/**
	 * Busca uma lista paginada tipo ponto pelo id modalidade
	 * @param id
	 * @param page
	 * @return Optional<Page<Tipo_Ponto>>
	 */ 
	Page<TipoPonto> BuscarPorModalidadeIdPaginavel(Long id_modalidade, PageRequest pageRequest);
	/**
	 * Salva um tipo ponto no banco de dados
	 * @param tipo_ponto
	 * @return
	 */
	TipoPonto Salvar(TipoPonto tipo_ponto);
	void Deletar(Long id);
}
