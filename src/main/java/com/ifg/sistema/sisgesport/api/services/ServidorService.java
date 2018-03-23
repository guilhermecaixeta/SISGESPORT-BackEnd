package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	 * Busca um servidor pela instituicao id
	 * @param id_instituicao
	 * @return
	 */
	Optional<List<Servidor>> findByCargoId(Long id_instituicao);
	/**
	 * Busca um servidor pela instituicao id paginado
	 * @param id_instituicao
	 * @param pageable
	 * @return
	 */
	Optional<Page<Servidor>> findByCargoInstituicaoId(Long id_instituicao, Pageable pageable);
	Servidor Salvar(Servidor servidor);
}
