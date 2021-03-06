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

import com.ifg.sistema.sisgesport.api.entities.TipoPonto;
import com.ifg.sistema.sisgesport.api.repositorios.TipoPontoRepositorio;
import com.ifg.sistema.sisgesport.api.services.TipoPontoService;
@Service
public class TipoPontoServiceImplementation implements TipoPontoService {
private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private TipoPontoRepositorio tipoPontoRepositorio;
	public Optional<TipoPonto> BuscarPorId(Long id) {
		log.info("Buscando servidor pelo id {} ", id);
		return Optional.ofNullable(tipoPontoRepositorio.findOne(id));
	}
	public Optional<TipoPonto> BuscarPorNome(String nome) {
		log.info("Buscando servidor pelo id {} ", nome);
		return Optional.ofNullable(tipoPontoRepositorio.findByNome(nome));
	}
	public Optional<List<TipoPonto>> BuscarPorModalidadeId(Long id_modalidade) {
		log.info("Buscando Tipo Ponto pelo id modalidade {} ", id_modalidade);
		return Optional.ofNullable(tipoPontoRepositorio.findByModalidadeId(id_modalidade));
	}
	public Page<TipoPonto> BuscarPorModalidadeIdPaginavel(Long id_modalidade, PageRequest pageRequest) {
		log.info("Buscando servidor pelo id modalidade {} ", id_modalidade);
		return tipoPontoRepositorio.findByModalidadeId(id_modalidade, pageRequest);
	}
	public Page<TipoPonto> BuscarTodosPaginavel(PageRequest pageRequest) {
		log.info("Buscando todos os tipos pontos ");
		return tipoPontoRepositorio.findAll( pageRequest);
	}
	public Optional<List<TipoPonto>> BuscarTodos() {
		log.info("Buscando uma lista de Tipo Ponto");
		return Optional.ofNullable(tipoPontoRepositorio.findAll());
	}
	@CachePut("BuscarDadosCacheTipoPonto")
	public TipoPonto Salvar(TipoPonto tipo_ponto) {
		log.info("Salvando um novo tipo ponto no banco de dados {} ", tipo_ponto);
		return tipoPontoRepositorio.save(tipo_ponto);
	}
	public void Deletar(Long id) {
		log.info("Deletando o tipo ponto com id: {}", id);
		tipoPontoRepositorio.delete(id);
	}
}
