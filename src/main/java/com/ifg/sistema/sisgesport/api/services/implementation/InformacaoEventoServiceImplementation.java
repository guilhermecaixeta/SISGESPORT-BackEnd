package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.InformacaoEvento;
import com.ifg.sistema.sisgesport.api.repositorios.InformacaoEventoRepositorio;
import com.ifg.sistema.sisgesport.api.services.InformacaoEventoService;
@Service
public class InformacaoEventoServiceImplementation implements InformacaoEventoService {
private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private InformacaoEventoRepositorio informacaoEventoRepositorio;
	public Optional<List<InformacaoEvento>> BuscarPorCodigoEvento(String codigo_evento) {
		log.info("Buscando informacao evento pelo codigo {} ",codigo_evento);
		return Optional.ofNullable(informacaoEventoRepositorio.findByEventoCodigoEvento(codigo_evento));
	}

	public Page<InformacaoEvento> BuscarPorEventoIdPaginavel(Long id_evento, PageRequest pageRequest) {
		log.info("Buscando informacao evento pelo id {} ",id_evento);
		return informacaoEventoRepositorio.findByEventoId(id_evento, pageRequest);
	}

	public Page<InformacaoEvento> BuscarPorCodigoEventoPaginavel(String codigo_evento, PageRequest pageRequest) {
		log.info("Buscando informacao evento pelo codigo {} ",codigo_evento);
		return informacaoEventoRepositorio.findByEventoCodigoEvento(codigo_evento, pageRequest);
	}

	public Optional<InformacaoEvento> BuscarPorId(Long id) {
		log.info("Buscando imagem pelo id da entidade comum {} ", id);
		return Optional.ofNullable(informacaoEventoRepositorio.findOne(id));
	}

	public InformacaoEvento Salvar(InformacaoEvento informacao_evento) {
		log.info("Salvando a informacao evento {} ",informacao_evento.getTitulo());
		return informacaoEventoRepositorio.save(informacao_evento);
	}
	public void Deletar(Long id) {
		log.info("Deletando a  informacao evento com id: {}", id);
		informacaoEventoRepositorio.delete(id);
	}
}
