package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.Tipo_Ponto;
import com.ifg.sistema.sisgesport.api.repositorios.TipoPontoRepositorio;
import com.ifg.sistema.sisgesport.api.services.TipoPontoService;
@Service
public class TipoPontoServiceImplementation implements TipoPontoService {
private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private TipoPontoRepositorio tipoPontoRepositorio;
	
	public Optional<Tipo_Ponto> BuscarPorId(Long id) {
		log.info("Buscando servidor pelo id {} ", id);
		return Optional.ofNullable(tipoPontoRepositorio.findOne(id));
	}

	public Optional<Tipo_Ponto> BuscarPorNome(String nome) {
		log.info("Buscando servidor pelo id {} ", nome);
		return Optional.ofNullable(tipoPontoRepositorio.findByNome(nome));
	}

	public Optional<List<Tipo_Ponto>> BuscarPorModalidadeId(Long id_modalidade) {
		log.info("Buscando Tipo Ponto pelo id modalidade {} ", id_modalidade);
		return Optional.ofNullable(tipoPontoRepositorio.findByModalidadeId(id_modalidade));
	}

	public Page<Tipo_Ponto> BuscarPorModalidadeIdPaginavel(Long id_modalidade, PageRequest pageRequest) {
		log.info("Buscando servidor pelo id modalidade {} ", id_modalidade);
		return tipoPontoRepositorio.findByModalidadeId(id_modalidade, pageRequest);
	}

	public Tipo_Ponto Salvar(Tipo_Ponto tipo_ponto) {
		log.info("Salvando tipo ponto {} ", tipo_ponto);
		return tipoPontoRepositorio.save(tipo_ponto);
	}

}
