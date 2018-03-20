package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Evento;
@Transactional(readOnly=true)
public interface EventoRepositorio extends JpaRepository<Evento, Long> {
	
	List<Evento> findByCriadorMatriculasiap(String matriculaSiap);
	
	Page<Evento> findByCriadorMatriculasiap(String matriculaSiap, Pageable page);
}
