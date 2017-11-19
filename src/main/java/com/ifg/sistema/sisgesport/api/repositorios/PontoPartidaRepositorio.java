package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Partida_Ponto;

public interface PontoPartidaRepositorio  extends JpaRepository<Partida_Ponto, Long> {

	@Transactional(readOnly=true)
	Partida_Ponto findByTipoPontoId(Integer id);
	
	@Transactional(readOnly=true)
	List<Partida_Ponto> findByPartidaId(Integer id);
	
	@Transactional(readOnly=true)
	List<Partida_Ponto> findByJogadorId(Integer id);
	
	@Transactional(readOnly=true)
	List<Partida_Ponto> findByJogadorTimeId(Integer id);
}
