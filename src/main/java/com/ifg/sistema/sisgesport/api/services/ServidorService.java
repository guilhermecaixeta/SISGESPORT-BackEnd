package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ifg.sistema.sisgesport.api.entities.Servidor;

public interface ServidorService {
	/**
	 * Busca uma servidor pelo id
	 * @param id
	 * @return
	 */
	Optional<Servidor> BuscarPorId(Long id);
	/**
	 * Busca um servidor pela matricula siap
	 * @param matriculaSiap
	 * @return
	 */
	Optional<Servidor> findByMatriculaSiap(String matriculaSiap);
	/**
	 * Busca uma lista de servidores pela instituicao id
	 * @param id_instituicao
	 * @return
	 */
	Optional<List<Servidor>> findByCargoId(Long id_instituicao);
	/**
	 * Busca uma lista paginada de servidores pelo instituicao id
	 * @param id_instituicao
	 * @param pageRequest
	 * @return
	 */
	Page<Servidor> findByCargoInstituicaoId(Long id_instituicao, PageRequest pageRequest);
	/**
	 * Salva um novo servidor no banco de dados
	 * @param servidor
	 * @return
	 */
	Servidor Salvar(Servidor servidor);
	void Deletar(Long id);
}
