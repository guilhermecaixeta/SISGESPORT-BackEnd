package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Evento;
import com.ifg.sistema.sisgesport.api.repositorios.base.EntidadeComumRepositorio;
@Transactional(readOnly=true)
public interface EventoRepositorio extends EntidadeComumRepositorio<Evento> {
	
	Evento findByCodigoEvento(String codigo_evento);
	
	List<Evento> findByCriadorMatricula(String matriculaSiap);
	
	Page<Evento> findByCriadorMatricula(String matriculaSiap, Pageable page);
}
