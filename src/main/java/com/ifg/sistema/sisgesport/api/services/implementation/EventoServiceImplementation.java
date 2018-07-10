package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.Evento;
import com.ifg.sistema.sisgesport.api.repositorios.EventoRepositorio;
import com.ifg.sistema.sisgesport.api.services.EventoService;
@Service
public class EventoServiceImplementation implements EventoService {
private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private EventoRepositorio eventoRepositorio;
	public Optional<List<Evento>> BuscarPorMatriculaCriador(String matriculaSiap) {
		log.info("Buscando o evento pela matricula Siap {} ",matriculaSiap);
		return Optional.ofNullable(eventoRepositorio.findByCriadorMatricula(matriculaSiap));
	}
	@Cacheable("BuscarDadosCache")
	public Page<Evento> BuscarPorMatriculaCriador(String matriculaSiap, PageRequest pageRequest) {
		log.info("Buscando o evento pela matricula Siap {} ",matriculaSiap);
		return eventoRepositorio.findByCriadorMatricula(matriculaSiap, pageRequest);
	}
	@Cacheable("BuscarDadosCache")
	public Optional<Evento> BuscarPorId(Long id) {
		log.info("Buscando modalidade evento pelo id do evento {} ",id);
		return Optional.ofNullable(eventoRepositorio.findOne(id));
	}
	@Cacheable("BuscarDadosCache")
	public Optional<Evento> BuscarPorCodigoEvento(String codigo_evento) {
		log.info("Buscando modalidade evento pelo codigo_evento {} ",codigo_evento);
		return Optional.ofNullable(eventoRepositorio.findByCodigoEvento(codigo_evento));
	}
    @CachePut("BuscarDadosCache")
	public Evento Salvar(Evento evento) {
		log.info("Salvando o evento {} ",evento.getNome());
		return eventoRepositorio.save(evento);
	}
	public void Deletar(Long id) {
		log.info("Deletando o evento com id: {}", id);
		eventoRepositorio.delete(id);
	}
}
