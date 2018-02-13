package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Time;

public interface TimeRepositorio extends JpaRepository<Time, Long> {

	@Transactional(readOnly=true)
	Time findById(Integer id);
	
	@Transactional(readOnly=true)
	List<Time> findByEquipeId(Integer id_equipe);
	
	@Transactional(readOnly=true)
	Page<Time> findByEquipeId(Integer id_equipe, Pageable page);
}
