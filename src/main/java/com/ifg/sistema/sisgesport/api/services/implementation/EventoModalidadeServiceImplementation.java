package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.EventoModalidade;
import com.ifg.sistema.sisgesport.api.repositorios.EventoModalidadeRepositorio;
import com.ifg.sistema.sisgesport.api.services.EventoModalidadeService;
@Service
public class EventoModalidadeServiceImplementation implements EventoModalidadeService {
	private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private EventoModalidadeRepositorio eventoModalidadeRepositorio;
	public Optional<List<EventoModalidade>> BuscarPorEventoId(Long id_evento){
		log.info("Buscando modalidade evento pelo id do evento {} ",id_evento);
		return Optional.ofNullable(eventoModalidadeRepositorio.findByEventoId(id_evento));
	}
	public Optional<List<EventoModalidade>> BuscarPorModalidadeId(Long id_modalidade){
		log.info("Buscando modalidade evento pelo id da modalidade {} ",id_modalidade);
		return Optional.ofNullable(eventoModalidadeRepositorio.findByModalidadeId(id_modalidade));
	}
	public Optional<EventoModalidade> BuscarPorId(Long id){
		log.info("Buscando modalidade evento pelo id {} ", id);
		return Optional.ofNullable(eventoModalidadeRepositorio.findOne(id));
	}
	public EventoModalidade Salvar(EventoModalidade evento_modalidade) {
		log.info("Salvando evento modalidade {} ", evento_modalidade);
		return eventoModalidadeRepositorio.save(evento_modalidade);
	}
	public void Deletar(Long id) {
		log.info("Deletando o evento modalidade com id: {}", id);
		eventoModalidadeRepositorio.delete(id);
	}
}
