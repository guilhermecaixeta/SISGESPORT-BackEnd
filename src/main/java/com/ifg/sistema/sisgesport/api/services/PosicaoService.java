package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.ifg.sistema.sisgesport.api.entities.Posicao;

public interface PosicaoService {
	/**
	 * Busca uma posicao pelo id
	 * @param id
	 * @return
	 */
	Optional<Posicao> BuscarPorId(Long id);
	/**
	 * Busca uma posicao pelo nome
	 * @param nome
	 * @return
	 */
	Optional<Posicao> BuscarPorNome(String nome);
	/**
	 * Busca uma lista de posicao pelo id da modalidade id
	 * @param id_modalidade
	 * @return
	 */
	Optional<List<Posicao>> BuscarPorModalidadeId(Long id_modalidade);
	/**
	 * Busca uma lista paginada de posicao pelo id da modalidade id
	 * @param id_modalidade
	 * @param pageRequest
	 * @return
	 */
	Page<Posicao> BuscarPorModalidadeIdPaginavel(Long id_modalidade, PageRequest pageRequest);
	/**
	 * Salva uma nova posicao no banco de dados
	 * @param salvar
	 * @return
	 */
	Posicao Salvar(Posicao salvar);
}
