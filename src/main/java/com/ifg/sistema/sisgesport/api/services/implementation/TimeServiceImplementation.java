package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.Time;
import com.ifg.sistema.sisgesport.api.repositorios.TimeRepositorio;
import com.ifg.sistema.sisgesport.api.services.TimeService;

@Service
public class TimeServiceImplementation implements TimeService{
private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private TimeRepositorio timeRepositorio;
	
	public Optional<Time> BuscarPorId(Long id) {
		log.info("Buscando servidor pelo id {} ", id);
		return Optional.ofNullable(timeRepositorio.findOne(id));
	}

	public Optional<Time> BuscarPorEquipeCodigoEquipe(String codigo) {
		log.info("Buscando time pelo codigo da equipe{} ", codigo);
		return Optional.ofNullable(timeRepositorio.findByEquipeCodigoEquipe(codigo));
	}

	public Optional<List<Time>> BuscarPorEquipeId(Long id_equipe) {
		log.info("Buscando time pelo id equipe {} ", id_equipe);
		return Optional.ofNullable(timeRepositorio.findByEquipeId(id_equipe));
	}

	public Optional<List<Time>> BuscarPorEventoIdEModalidadeId(Long id_evento, Long id_modalidade) {
		log.info("Buscando times pelo id evento {0} e id modalidade {1}", id_evento, id_modalidade);
		return Optional.ofNullable(timeRepositorio.findByEquipeEventoIdAndEventoModalidadeModalidadeId(id_evento, id_modalidade));
	}

	public Page<Time> BuscarPorEquipeIdPaginavel(Long id_equipe, PageRequest pageRequest) {
		log.info("Buscando time pelo id equipe {} ", id_equipe);
		return timeRepositorio.findByEquipeId(id_equipe, pageRequest);
	}

	public Page<Time> BuscarPorEventoIdPaginavel(Long id_evento, Long id_modalidade, char sexo, PageRequest pageRequest) {
		log.info("Buscando time pelo id evento {} ", id_evento);
		return timeRepositorio.findByEquipeEventoIdAndEventoModalidadeModalidadeIdAndEventoModalidadeSexo
						( id_evento, id_modalidade, sexo, pageRequest);
	}

	public Time Salvar(Time time) {
		log.info("Salvando novo time no banco de dados {} ", time);
		return timeRepositorio.save(time);
	}
	public void Deletar(Long id) {
		log.info("Deletando o time com id: {}", id);
		timeRepositorio.delete(id);
	}
}
