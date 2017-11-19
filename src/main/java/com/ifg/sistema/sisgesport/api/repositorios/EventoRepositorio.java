package com.ifg.sistema.sisgesport.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Evento;

public interface EventoRepositorio extends JpaRepository<Evento, Long> {

	@Transactional(readOnly=true)
	Evento findById(Integer id);
	
	@Transactional(readOnly=true)
	Evento findByCriadorMatriculasiap(String matriculaSiap);
}
