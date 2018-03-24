package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.Posicao;
import com.ifg.sistema.sisgesport.api.repositorios.PosicaoRepositorio;
import com.ifg.sistema.sisgesport.api.services.PosicaoService;

@Service
public class PosicaoServiceImplementation implements PosicaoService {
private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private PosicaoRepositorio posicaoRepositorio;

	public Optional<Posicao> BuscarPorId(Long id) {
		log.info("Buscando Penalidade pelo nome {} ", id);
		return Optional.ofNullable(posicaoRepositorio.findOne(id));
	}

	public Optional<Posicao> BuscarPorNome(String nome) {
		log.info("Buscando Penalidade pelo nome {} ", nome);
		return Optional.ofNullable(posicaoRepositorio.findByNome(nome));
	}

	public Optional<List<Posicao>> BuscarPorModalidadeId(Long id_modalidade) {
		log.info("Buscando Penalidade pelo nome {} ", id_modalidade);
		return Optional.ofNullable(posicaoRepositorio.findByModalidadeId(id_modalidade));
	}

	public Page<Posicao> BuscarPorModalidadeIdPaginavel(Long id_modalidade, PageRequest pageRequest) {
		log.info("Buscando Penalidade pelo nome {} ", id_modalidade);
		return posicaoRepositorio.findByModalidadeId(id_modalidade, pageRequest);
	}

	@Override
	public Posicao Salvar(Posicao posicao) {
		log.info("Buscando Penalidade pelo nome {} ", posicao);
		return posicaoRepositorio.save(posicao);
	}
}
