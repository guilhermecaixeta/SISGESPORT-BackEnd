package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifg.sistema.sisgesport.api.entities.PartidaPonto;

public interface PartidaPontoRepositorio  extends JpaRepository<PartidaPonto, Long> {

	List<PartidaPonto> findByJogadorId(Long id_jogador);
	List<PartidaPonto> findByPartidaId(Long id_partida);
	List<PartidaPonto> findByTipoPontoId(Long id_penalidade);
}
