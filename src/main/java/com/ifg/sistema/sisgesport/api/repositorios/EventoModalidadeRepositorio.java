package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import com.ifg.sistema.sisgesport.api.entities.EventoModalidade;

@Transactional(readOnly=true)
public interface EventoModalidadeRepositorio extends JpaRepository<EventoModalidade, Long> {
	
	List<EventoModalidade> findByModalidadeId(Long id_modalidade);
	List<EventoModalidade> findByEventoId(Long id_evento);
}
