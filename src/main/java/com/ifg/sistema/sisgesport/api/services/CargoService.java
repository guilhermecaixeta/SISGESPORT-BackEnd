	package com.ifg.sistema.sisgesport.api.services;
	
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
	
import com.ifg.sistema.sisgesport.api.entities.Cargo;
	
	public interface CargoService {
	
	/**
	 * Busca o cargo pelo nome
	 * @param nome
	 * @return Optional<Cargo>
	 */
	Optional<List<Cargo>> BuscarPorNome(String nome);
	/**
	 * Busca o cargo pelo id
	 * @param id
	 * @return Optional<Cargo>
	 */
	Optional<Cargo> BuscarPorId(Long id);
	/**
	 * Busca o cargo pelo id da instituicao
	 * @param id
	 * @return Optional<Cargo>
	 */
	Optional<List<Cargo>> BuscarPorInstituicaoId(Long id_instituicao);
	/**
	 * Busca o cargo pelo id da instituicao e realiza a paginacao
	 * @param id
	 * @param page
	 * @return Optional<Cargo>
	 */
	Page<Cargo> BuscarPorInstituicaoIdPaginavel(Long id, PageRequest pageRequest);
	/**
	 * Salva um novo cargo
	 * @param cargo
	 * @return
	 */
	Cargo Salvar(Cargo cargo);
	}
