package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.InformacaoEvento;
@Transactional(readOnly=true)
public interface InformacaoEventoRepositorio extends JpaRepository<InformacaoEvento, Long> {
		
	List<InformacaoEvento> findByEventoCodigoEvento(String codigo_evento);
	
	Page<InformacaoEvento> findByEventoCodigoEvento(String codigo_evento, Pageable page);
}
