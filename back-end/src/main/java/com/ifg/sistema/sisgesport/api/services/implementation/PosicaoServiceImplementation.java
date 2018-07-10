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

import com.ifg.sistema.sisgesport.api.entities.Posicao;
import com.ifg.sistema.sisgesport.api.repositorios.PosicaoRepositorio;
import com.ifg.sistema.sisgesport.api.services.PosicaoService;

@Service
public class PosicaoServiceImplementation implements PosicaoService {
private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private PosicaoRepositorio posicaoRepositorio;
	@Cacheable("BuscarDadosCache")
	public Optional<Posicao> BuscarPorId(Long id) {
		log.info("Buscando Posicao pelo id {} ", id);
		return Optional.ofNullable(posicaoRepositorio.findOne(id));
	}
	@Cacheable("BuscarDadosCache")
	public Optional<Posicao> BuscarPorNome(String nome) {
		log.info("Buscando Posicao pelo nome {} ", nome);
		return Optional.ofNullable(posicaoRepositorio.findByNomeEquals(nome));
	}
	@Cacheable("BuscarDadosCache")
	public Optional<List<Posicao>> BuscarPorModalidadeId(Long id_modalidade) {
		log.info("Buscando Posicao pelo nome id modalidade {} ", id_modalidade);
		return Optional.ofNullable(posicaoRepositorio.findByModalidadeId(id_modalidade));
	}
	@Cacheable("BuscarDadosCache")
	public Page<Posicao> BuscarPorModalidadeIdPaginavel(Long id_modalidade, PageRequest pageRequest) {
		log.info("Buscando uma lista paginada de Posicao pelo id modalidade {} ", id_modalidade);
		return posicaoRepositorio.findByModalidadeId(id_modalidade, pageRequest);
	}
	@CachePut("BuscarDadosCache")
	public Posicao Salvar(Posicao posicao) {
		log.info("Salvando uma nova posicao no banco de dados {} ", posicao);
		return posicaoRepositorio.save(posicao);
	}
	
	public void Deletar(Long id) {
		log.info("Deletando o posicao com id: {}", id);
		posicaoRepositorio.delete(id);
	}
}
