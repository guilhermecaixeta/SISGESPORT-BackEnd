package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import com.ifg.sistema.sisgesport.api.entities.Evento_Modalidade;

@Transactional(readOnly=true)
public interface EventoModalidadeRepositorio extends JpaRepository<Evento_Modalidade, Long> {
	
	List<Evento_Modalidade> findByModalidadeId(Long id_modalidade);
	List<Evento_Modalidade> findByEventoId(Long id_evento);
}
