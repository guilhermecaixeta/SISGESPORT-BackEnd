package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.Penalidade;
import com.ifg.sistema.sisgesport.api.repositorios.PenalidadeRepositorio;
import com.ifg.sistema.sisgesport.api.services.PenalidadeService;

@Service
public class PenalidadeServiceImpementation implements PenalidadeService {
private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private PenalidadeRepositorio penalidadeRepositorio;

	public Optional<Penalidade> BuscarPorId(Long id) {
		log.info("Buscando Partida pelo id time casa {} ", id);
		return Optional.ofNullable(penalidadeRepositorio.findOne(id));
	}

	public Optional<Penalidade> BuscarPorNome(String nome) {
		log.info("Buscando Penalidade pelo nome {} ", nome);
		return Optional.ofNullable(penalidadeRepositorio.findByNome(nome));
	}

	public Optional<List<Penalidade>> BuscarPorModalidadeId(Long id_modalidade) {
		log.info("Buscando Partida pelo id time casa {} ", id_modalidade);
		return Optional.ofNullable(penalidadeRepositorio.findByModalidadeId(id_modalidade));
	}

	public Penalidade Salvar(Penalidade penalidade) {
		log.info("Buscando Partida pelo id time casa {} ", penalidade);
		return penalidadeRepositorio.save(penalidade);
	}
}
