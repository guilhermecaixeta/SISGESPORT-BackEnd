package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Evento;
import com.ifg.sistema.sisgesport.api.entities.Informacao_Evento;
@Transactional(readOnly=true)
public interface InformacaoEventoRepositorio extends JpaRepository<Informacao_Evento, Long> {
	
	Informacao_Evento findById(Integer id);
	
	List<Informacao_Evento> findByEvento(Evento evento);
	
	Page<Informacao_Evento> findByEvento(Evento evento, Pageable page);
}
