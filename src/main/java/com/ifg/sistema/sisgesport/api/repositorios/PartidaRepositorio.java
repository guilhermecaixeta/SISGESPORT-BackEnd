package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Partida;
@Transactional(readOnly=true)
public interface PartidaRepositorio  extends JpaRepository<Partida, Long> {

	List<Partida> findById(Integer id);
	
	List<Partida> findByTimeCasaId(Integer id_time_casa);
	
	Page<Partida> findByTimeCasaId(Integer id_time_casa, Pageable page);
	
	List<Partida> findByEventoId(Integer id_evento);
	
	Page<Partida> findByEventoId(Integer id_evento, Pageable page);
	}
