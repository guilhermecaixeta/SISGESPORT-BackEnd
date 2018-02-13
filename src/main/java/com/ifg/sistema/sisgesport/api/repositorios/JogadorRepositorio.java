package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Jogador;

public interface JogadorRepositorio extends JpaRepository<Jogador, Long> {

	@Transactional(readOnly=true)
	Jogador findById(Integer id);
	
	@Transactional(readOnly=true)
	List<Jogador> findByTimeId(Integer id_time);
	
	@Transactional(readOnly=true)
	List<Jogador> findByTimeEquipeId(Integer id_equipe);
	
	@Transactional(readOnly=true)
	Page<Jogador> findByTimeId(Integer id_time, Pageable page);
	
	@Transactional(readOnly=true)
	Page<Jogador> findByTimeEquipeId(Integer id_equipe, Pageable page);
}
