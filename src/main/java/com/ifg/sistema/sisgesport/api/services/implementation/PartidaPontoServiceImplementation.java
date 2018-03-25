package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.Partida_Ponto;
import com.ifg.sistema.sisgesport.api.repositorios.PartidaPontoRepositorio;
import com.ifg.sistema.sisgesport.api.services.PartidaPontoService;
@Service
public class PartidaPontoServiceImplementation implements PartidaPontoService {
private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private PartidaPontoRepositorio partidaPontoRepositorio;
	public Optional<Partida_Ponto> BuscarPorId(Long id) {
		log.info("Buscando Partida Ponto pelo id {} ", id);
		return Optional.ofNullable(partidaPontoRepositorio.findOne(id));
	}

	public Optional<List<Partida_Ponto>> BuscarPorJogadorId(Long id_jogador) {
		log.info("Buscando Partida Ponto pelo jogador id {} ", id_jogador);
		return Optional.ofNullable(partidaPontoRepositorio.findByJogadorId(id_jogador));
	}

	public Optional<List<Partida_Ponto>> BuscarPorPartidaId(Long id_partida) {
		log.info("Buscando Partida Ponto pela partida id {} ", id_partida);
		return Optional.ofNullable(partidaPontoRepositorio.findByPartidaId(id_partida));
	}

	public Optional<List<Partida_Ponto>> BuscarPorTipoPontoId(Long id_tipo_ponto) {
		log.info("Buscando Partida Ponto pelo tipo ponto id {} ", id_tipo_ponto);
		return Optional.ofNullable(partidaPontoRepositorio.findByTipoPontoId(id_tipo_ponto));
	}

	public Partida_Ponto Salvar(Partida_Ponto partida_ponto) {
		log.info("Buscando Partida Ponto pelo nome {} ", partida_ponto);
		return partidaPontoRepositorio.save(partida_ponto);
	}
	public void Deletar(Long id) {
		log.info("Deletando o Partida Ponto com id: {}", id);
		partidaPontoRepositorio.delete(id);
	}
}
