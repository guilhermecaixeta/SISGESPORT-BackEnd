package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifg.sistema.sisgesport.api.entities.Partida_Ponto;

public interface PartidaPontoRepositorio  extends JpaRepository<Partida_Ponto, Long> {

	List<Partida_Ponto> findByJogadorId(Long id_jogador);
	List<Partida_Ponto> findByPartidaId(Long id_partida);
	List<Partida_Ponto> findByTipoPontoId(Long id_penalidade);
}
