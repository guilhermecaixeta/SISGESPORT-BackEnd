package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.PartidaPenalidade;

@Transactional(readOnly=true)
public interface PartidaPenalidadeRepositorio extends JpaRepository<PartidaPenalidade, Long> {

	List<PartidaPenalidade> findByJogadorId(Long id_jogador);
	List<PartidaPenalidade> findByPartidaId(Long id_partida);
	List<PartidaPenalidade> findByPenalidadeId(Long id_penalidade);
}
