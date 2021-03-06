package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.List;
import java.util.Optional;

import com.ifg.sistema.sisgesport.api.entities.Posicao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
		log.info("Buscando Penalidade pelo id {} ", id);
		return Optional.ofNullable(penalidadeRepositorio.findOne(id));
	}

	public Optional<Penalidade> BuscarPorNome(String nome) {
		log.info("Buscando Penalidade pelo nome {} ", nome);
		return Optional.ofNullable(penalidadeRepositorio.findByNome(nome));
	}

	public Optional<List<Penalidade>> BuscarPorModalidadeId(Long id_modalidade) {
		log.info("Buscando Penalidade pelo id modalidade {} ", id_modalidade);
		return Optional.ofNullable(penalidadeRepositorio.findByModalidadeId(id_modalidade));
	}

    public Page<Penalidade> BuscarPorModalidadeIdPaginavel(Long id_modalidade, PageRequest pageRequest) {
        log.info("Buscando uma lista paginada de Posicao pelo id modalidade {} ", id_modalidade);
        return penalidadeRepositorio.findByModalidadeId(id_modalidade, pageRequest);
    }

    public Page<Penalidade> BuscarTodosPaginavel(PageRequest pageRequest) {
        log.info("Buscando uma lista paginada de Penalidade");
        return penalidadeRepositorio.findAll(pageRequest);
    }
	public Optional<List<Penalidade>> BuscarTodos() {
		log.info("Buscando uma lista de Tipo Ponto");
		return Optional.ofNullable(penalidadeRepositorio.findAll());
	}
	@CachePut("BuscarDadosCachePenalidade")
	public Penalidade Salvar(Penalidade penalidade) {
		log.info("Salvando uma nova Penalidade no banco de dados {} ", penalidade);
		return penalidadeRepositorio.save(penalidade);
	}
	public void Deletar(Long id) {
		log.info("Deletando o Penalidade com id: {}", id);
		penalidadeRepositorio.delete(id);
	}
}
