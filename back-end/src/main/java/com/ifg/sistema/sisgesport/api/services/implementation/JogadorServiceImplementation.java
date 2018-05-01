package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.Jogador;
import com.ifg.sistema.sisgesport.api.repositorios.JogadorRepositorio;
import com.ifg.sistema.sisgesport.api.services.JogadorService;
@Service
public class JogadorServiceImplementation implements JogadorService {
private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private JogadorRepositorio jogadorRepositorio;
	public Optional<Jogador> BuscarPorId(Long id) {
		log.info("Buscando iInstituicao pelo nome {} ", id);
		return Optional.ofNullable(jogadorRepositorio.findOne(id));
	}

	public Optional<List<Jogador>> BuscarPorTimeId(Long id_time) {
		log.info("Buscando Jogadores pelo id do time {} ",id_time);
		return Optional.ofNullable(jogadorRepositorio.findByTimeEquipeId(id_time));
	}

	public Optional<List<Jogador>> BuscarPorEquipeId(Long id_equipe) {
		log.info("Buscando Jogadores pelo id da equipe {} ",id_equipe);
		return Optional.ofNullable(jogadorRepositorio.findByTimeEquipeId(id_equipe));
	}

	public Page<Jogador> BuscarPorTimeIdPaginavel(Long id_time, PageRequest pageRequest) {
		log.info("Buscando Jogadores pelo id do time {} ",id_time);
		return jogadorRepositorio.findByTimeId(id_time, pageRequest);
	}

	public Page<Jogador> BuscarPorEquipeIdPaginavel(Long id_equipe, PageRequest pageRequest) {
		log.info("Buscando Jogadores pelo id da equipe",id_equipe);
		return jogadorRepositorio.findByTimeEquipeId(id_equipe, pageRequest);
	}

	public Jogador Salvar(Jogador jogador) {
		log.info("Salvando o jogador {} ",jogador.getJogador());
		return jogadorRepositorio.save(jogador);
	}
	public void Deletar(Long id) {
		log.info("Deletando o jogador com id: {}", id);
		jogadorRepositorio.delete(id);
	}
}
