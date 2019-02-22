package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.Partida;
import com.ifg.sistema.sisgesport.api.repositorios.PartidaRepositorio;
import com.ifg.sistema.sisgesport.api.services.PartidaService;

@Service
public class 	PartidaServiceImplementation implements PartidaService {
private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private PartidaRepositorio partidaRepositorio;

	public Optional<Partida> BuscarPorId(Long id) {
		log.info("Buscando Partida pelo id time casa {} ", id);
		return Optional.ofNullable(partidaRepositorio.findOne(id));
	}

	public Optional<List<Partida>> BuscarPorTimeCasaId(Long id_time_casa) {
		log.info("Buscando Partida pelo id time casa {} ", id_time_casa);
		return Optional.ofNullable(partidaRepositorio.findByTimeCasaId(id_time_casa));
	}

	public Page<Partida> BuscarPorTimeCasaIdPaginavel(Long id_time_casa, PageRequest pageRequest) {
		log.info("Buscando Partida pelo id time casa{} ", id_time_casa);
		return partidaRepositorio.findByTimeCasaId(id_time_casa, pageRequest);
	}

	public Optional<List<Partida>> BuscarPorEventoId(Long id_evento) {
		log.info("Buscando Partida pelo id evento {} ", id_evento);
		return Optional.ofNullable(partidaRepositorio.findByEventoId(id_evento));
	}
	public Optional<List<Partida>> BuscarPorEventoIdEModalidadeId(Long id_evento, Long id_modalidade) {
		log.info("Buscando Partida pelo id evento {} e id modalidade {}", id_evento, id_modalidade);
		return Optional.ofNullable(partidaRepositorio.findByEventoIdAndTimeCasaEventoModalidadeModalidadeId(id_evento, id_modalidade));
	}

	public Page<Partida> BuscarPorEventoIdPaginavel(Long id_evento, PageRequest pageRequest) {
		log.info("Buscando Partida pelo id evento {} ", id_evento);
		return partidaRepositorio.findByEventoId(id_evento, pageRequest);
	}
	public Partida Salvar(Partida partida) {
		log.info("Salvando a partida {} ", partida);
		return partidaRepositorio.save(partida);
	}
	public void Deletar(Long id) {
		log.info("Deletando o Partida com id: {}", id);
		partidaRepositorio.delete(id);
	}
}
