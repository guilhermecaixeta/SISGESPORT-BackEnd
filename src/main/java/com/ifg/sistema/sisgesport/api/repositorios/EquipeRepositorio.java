package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Equipe;
@Transactional(readOnly=true)
public interface EquipeRepositorio extends JpaRepository<Equipe, Long> {

	Equipe findByCodigoEquipe(String codigo);
	
	Equipe findByCapitaoMatricula(String matricula);
	
	List<Equipe> findByEventoId(Long id_evento);
	
	Page<Equipe> findByEventoId(Long id_evento, Pageable pageable);
}
