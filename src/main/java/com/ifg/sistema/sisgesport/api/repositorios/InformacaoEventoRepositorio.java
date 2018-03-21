package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Informacao_Evento;
@Transactional(readOnly=true)
public interface InformacaoEventoRepositorio extends JpaRepository<Informacao_Evento, Long> {
		
	List<Informacao_Evento> findByEventoCodigoEvento(String codigo_evento);
	
	Page<Informacao_Evento> findByEventoCodigoEvento(String codigo_evento, Pageable page);
}
