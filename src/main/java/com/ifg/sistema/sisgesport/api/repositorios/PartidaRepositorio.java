package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Partida;

public interface PartidaRepositorio  extends JpaRepository<Partida, Long> {

	@Transactional(readOnly=true)
	List<Partida> findById(Integer id);
	
	@Transactional(readOnly=true)
	List<Partida> findByEventoId(Integer id_evento);
	
	@Transactional(readOnly=true)
	Page<Partida> findByEvento(Integer id_evento, Pageable page);
	}
