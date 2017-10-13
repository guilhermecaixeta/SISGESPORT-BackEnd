package com.ifg.sistema.sisgesport.api.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Equipe;

public interface EquipeRepositorio extends JpaRepository<Equipe, Long> {

	@Transactional(readOnly=true)
	Equipe findByCodigo(String codigo);
	
	@Transactional(readOnly=true)
	Page<Equipe> findByEvento(long id, Pageable pageable);
}
