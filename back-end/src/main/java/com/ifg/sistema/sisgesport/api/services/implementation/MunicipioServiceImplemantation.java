package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.Municipio;
import com.ifg.sistema.sisgesport.api.repositorios.MunicipioRepositorio;
import com.ifg.sistema.sisgesport.api.services.MunicipioService;
@Service
public class MunicipioServiceImplemantation implements MunicipioService {
private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private MunicipioRepositorio municipioRepositorio;
	public Optional<Municipio> BuscarPorId(Long id) {
		log.info("Buscando logradouro pelo nome {} ", id);
		return Optional.ofNullable(municipioRepositorio.findOne(id));
	}

	public Optional<Municipio> BuscarPorCepMunicipio(String cepMunicipio) {
		log.info("Buscando logradouro pelo nome {} ", cepMunicipio);
		return Optional.ofNullable(municipioRepositorio.findByCepMunicipio(cepMunicipio));
	}

	public Optional<Municipio> BuscarPorNomeOrSigla(String nome, String sigla) {
		log.info("Buscando logradouro pelo nome {1} ou pela sigla {2} ", nome, sigla);
		return Optional.ofNullable(municipioRepositorio.findByNomeOrSigla(nome, sigla));
	}

	public Optional<List<Municipio>> BuscarPorEstadoId(Long id_estado) {
		log.info("Buscando logradouro pelo nome, id_estado {} ", id_estado);
		return Optional.ofNullable(municipioRepositorio.findByEstadoId(id_estado));
	}

	public Page<Municipio> BuscarPorEstadoIdPaginavel(Long id_estado, PageRequest pageRequest) {
		log.info("Buscando logradouro pelo id_estado {} ", id_estado);
		return municipioRepositorio.findByEstadoId(id_estado, pageRequest);
	}

	public Municipio Salvar(Municipio municipio) {
		log.info("Salvando o novo município no banco de dados {}", municipio.getNome());
		return municipioRepositorio.save(municipio);
	}
	public void Deletar(Long id) {
		log.info("Deletando o município com id: {}", id);
		municipioRepositorio.delete(id);
	}
}
