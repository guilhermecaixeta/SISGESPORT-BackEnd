package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Jogador;
@Transactional(readOnly=true)
public interface JogadorRepositorio extends JpaRepository<Jogador, Long> {
	
	List<Jogador> findByTimeId(Long id_time);
	
	List<Jogador> findByTimeEquipeId(Long id_equipe);
	
	Page<Jogador> findByTimeId(Long id_time, Pageable page);
	
	Page<Jogador> findByTimeEquipeId(Long id_equipe, Pageable page);
}
