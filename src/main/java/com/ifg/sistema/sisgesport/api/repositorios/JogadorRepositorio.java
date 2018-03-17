package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Jogador;
@Transactional(readOnly=true)
public interface JogadorRepositorio extends JpaRepository<Jogador, Long> {

	Jogador findById(Integer id);
	
	List<Jogador> findByTimeId(Integer id_time);
	
	List<Jogador> findByTimeEquipeId(Integer id_equipe);
	
	Page<Jogador> findByTimeId(Integer id_time, Pageable page);
	
	Page<Jogador> findByTimeEquipeId(Integer id_equipe, Pageable page);
}
