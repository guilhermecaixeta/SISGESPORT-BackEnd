package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Equipe;
@Transactional(readOnly=true)
public interface EquipeRepositorio extends JpaRepository<Equipe, Integer> {

	Equipe findByCodigoEquipe(String codigo);

	List<Equipe> findByEventoId(Integer id);
	
	Page<Equipe> findByEventoId(Integer id_equipe, Pageable pageable);
}
