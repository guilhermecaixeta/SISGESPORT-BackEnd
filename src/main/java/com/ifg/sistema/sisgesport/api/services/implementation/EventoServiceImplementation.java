package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ifg.sistema.sisgesport.api.entities.Evento;
import com.ifg.sistema.sisgesport.api.repositorios.EventoRepositorio;
import com.ifg.sistema.sisgesport.api.services.EventoService;

public class EventoServiceImplementation implements EventoService {
private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private EventoRepositorio eventoRepositorio;
	public Optional<List<Evento>> BuscarPorMatriculaCriador(String matriculaSiap) {
		log.info("Buscando o evento pela matricula Siap {} ",matriculaSiap);
		return Optional.ofNullable(eventoRepositorio.findByCriadorMatriculaSiap(matriculaSiap));
	}

	public Optional<Page<Evento>> BuscarPorMatriculaCriador(String matriculaSiap, Pageable page) {
		log.info("Buscando o evento pela matricula Siap {} ",matriculaSiap);
		return Optional.ofNullable(eventoRepositorio.findByCriadorMatriculaSiap(matriculaSiap, page));
	}

	public Optional<Evento> BuscarPorId(Long id) {
		log.info("Buscando modalidade evento pelo id do evento {} ",id);
		return Optional.ofNullable(eventoRepositorio.findOne(id));
	}

	public Evento Salvar(Evento evento) {
		log.info("Salvando o evento {} ",evento.getNome());
		return eventoRepositorio.save(evento);
	}

}
