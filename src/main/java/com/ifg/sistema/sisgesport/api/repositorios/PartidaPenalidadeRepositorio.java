package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Partida_Penalidade;

@Transactional(readOnly=true)
public interface PartidaPenalidadeRepositorio extends JpaRepository<Partida_Penalidade, Long> {

	List<Partida_Penalidade> findByJogadorId(Long id_jogador);
	List<Partida_Penalidade> findByPartidaId(Long id_partida);
	List<Partida_Penalidade> findByPenalidadeId(Long id_penalidade);
}
