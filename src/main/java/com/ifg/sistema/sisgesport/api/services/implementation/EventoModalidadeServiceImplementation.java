package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ifg.sistema.sisgesport.api.entities.Evento_Modalidade;
import com.ifg.sistema.sisgesport.api.repositorios.EventoModalidadeRepositorio;
import com.ifg.sistema.sisgesport.api.services.EventoModalidadeService;

public class EventoModalidadeServiceImplementation implements EventoModalidadeService {
	private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private EventoModalidadeRepositorio eventoModalidadeRepositorio;
	public Optional<List<Evento_Modalidade>> BuscarPorEventoId(Long id_evento){
		log.info("Buscando modalidade evento pelo id do evento {} ",id_evento);
		return Optional.ofNullable(eventoModalidadeRepositorio.findByEventoId(id_evento));
	}
	public Optional<List<Evento_Modalidade>> BuscarPorModalidadeId(Long id_modalidade){
		log.info("Buscando modalidade evento pelo id da modalidade {} ",id_modalidade);
		return Optional.ofNullable(eventoModalidadeRepositorio.findByModalidadeId(id_modalidade));
	}
	public Optional<Evento_Modalidade> BuscarPorId(Long id){
		log.info("Buscando modalidade evento pelo id {} ", id);
		return Optional.ofNullable(eventoModalidadeRepositorio.findOne(id));
	}
	public Evento_Modalidade Salvar(Evento_Modalidade evento_modalidade) {
		log.info("Salvando evento modalidade {} ", evento_modalidade.getEvento().getNome());
		return eventoModalidadeRepositorio.save(evento_modalidade);
	}
}
