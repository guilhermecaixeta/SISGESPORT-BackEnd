package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;

import com.ifg.sistema.sisgesport.api.entities.Instituicao;

public interface InstituicaoService {
	/**
	 * Busca todas as instituicoes cadastradas
	 * @param nome
	 * @return Optional<Instituicao>
	 */
	Optional<List<Instituicao>> BuscarTodos();
	/**
	 * Busca uma instituicao pelo nome
	 * @param nome
	 * @return Optional<Instituicao>
	 */
	Optional<Instituicao> BuscarPorNomeInstituicao(String nome);
	/**
	 * Busca uma instituicao pelo id
	 * @param nome
	 * @return Optional<Instituicao>
	 */
	Optional<Instituicao> BuscarPorId(Long id);
	/**
	 * Salva uma nova instituicao no banco de dados
	 * @param instituicao
	 * @return
	 */
	Instituicao Salvar(Instituicao instituicao);
	void Deletar(Long id);
}
