package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.PartidaPenalidade;
import com.ifg.sistema.sisgesport.api.repositorios.PartidaPenalidadeRepositorio;
import com.ifg.sistema.sisgesport.api.services.PartidaPenalidadeService;
@Service
public class PartidaPenalidadeServiceImplementation implements PartidaPenalidadeService {
private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private PartidaPenalidadeRepositorio partidaPenalidadeRepositorio;
	public Optional<PartidaPenalidade> BuscarPorId(Long id) {
		log.info("Buscando logradouro pelo nome {} ", id);
		return Optional.ofNullable(partidaPenalidadeRepositorio.findOne(id));
	}

	public Optional<List<PartidaPenalidade>> BuscarPorJogadorId(Long id_jogador) {
		log.info("Buscando logradouro pelo id jogador {} ", id_jogador);
		return Optional.ofNullable(partidaPenalidadeRepositorio.findByJogadorId(id_jogador));
	}

	public Optional<List<PartidaPenalidade>> BuscarPorPartidaId(Long id_partida) {
		log.info("Buscando logradouro pelo id partida {} ", id_partida);
		return Optional.ofNullable(partidaPenalidadeRepositorio.findByPartidaId(id_partida));
	}

	public Optional<List<PartidaPenalidade>> BuscarPorPenalidadeId(Long id_penalidade) {
		log.info("Buscando logradouro pelo id penalidade {} ", id_penalidade);
		return Optional.ofNullable(partidaPenalidadeRepositorio.findByPenalidadeId(id_penalidade));
	}

	public PartidaPenalidade Salvar(PartidaPenalidade partida_penalidade) {
		log.info("Salvando nova partida penalidade no banco de dados");
		return partidaPenalidadeRepositorio.save(partida_penalidade);
	}
	public void Deletar(Long id) {
		log.info("Deletando o partida penalidade com id: {}", id);
		partidaPenalidadeRepositorio.delete(id);
	}
}
