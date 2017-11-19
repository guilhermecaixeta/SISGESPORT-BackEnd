package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Equipe;

public interface EquipeRepositorio extends JpaRepository<Equipe, Integer> {

	@Transactional(readOnly=true)
	Equipe findByCodigo(String codigo);

	@Transactional(readOnly=true)
	List<Equipe> findByEventoId(Integer id);
	
	@Transactional(readOnly=true)
	Page<Equipe> findByEventoId(Integer id, Pageable pageable);
}
