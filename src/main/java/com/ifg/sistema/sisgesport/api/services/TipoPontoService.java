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
	 * @param id_modalidade
	 * @return Optional<List<Tipo_Ponto>>
	 */
	Optional<List<TipoPonto>> BuscarPorModalidadeId(Long id_modalidade);
	/**
	 * Busca uma lista paginada tipo ponto pelo id modalidade
	 * @param id_modalidade
	 * @param pageRequest
	 * @return Optional<Page<Tipo_Ponto>>
	 */ 
	Page<TipoPonto> BuscarPorModalidadeIdPaginavel(Long id_modalidade, PageRequest pageRequest);
	/**
	 * Busca uma lista paginada tipo ponto
	 * @param pageRequest
	 * @return Optional<Page<Tipo_Ponto>>
	 */
	Page<TipoPonto> BuscarTodosPaginavel(PageRequest pageRequest);
	/**
	 * Busca uma lista paginada tipo ponto
	 * @return Optional<Page<Tipo_Ponto>>
	 */
    Optional<List<TipoPonto>> BuscarTodos();
	/**
	 * Salva um tipo ponto no banco de dados
	 * @param tipo_ponto
	 * @return
	 */
	TipoPonto Salvar(TipoPonto tipo_ponto);
	void Deletar(Long id);
}
