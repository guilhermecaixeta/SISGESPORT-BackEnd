package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;
import com.ifg.sistema.sisgesport.api.entities.Penalidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface PenalidadeService {
	/**
	 * Busca uma penalidad pelo id
	 * @param id
	 * @return
	 */
	Optional<Penalidade> BuscarPorId(Long id);
	/**
	 * Busca uma penalidade pelo nome
	 * @param nome
	 * @return
	 */
	Optional<Penalidade> BuscarPorNome(String nome);
	/**
	 * Busca uma penalidade pela modalidade id
	 * @param id
	 * @return
	 */
	Optional<List<Penalidade>> BuscarPorModalidadeId(Long id_modalidade);
	/**
	 * Busca uma lista paginada de penalidade pelo id da modalidade
	 * @param id_modalidade
	 * @param pageRequest
	 * @return
	 */
	Page<Penalidade> BuscarPorModalidadeIdPaginavel(Long id_modalidade, PageRequest pageRequest);
	/**
	 * Busca uma lista paginada de penalidade
	 * @param pageRequest
	 * @return
	 */
	Page<Penalidade> BuscarTodosPaginavel(PageRequest pageRequest);
	/**
	 * Salva uma nova penalidade no banco de dados
	 * @param penalidade
	 * @return
	 */
	Penalidade Salvar(Penalidade penalidade);
	void Deletar(Long id);
}
