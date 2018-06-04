package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.Instituicao;
import com.ifg.sistema.sisgesport.api.repositorios.InstituicaoRepositorio;
import com.ifg.sistema.sisgesport.api.services.InstituicaoService;
@Service
public class InstituicaoServiceImplementation implements InstituicaoService {
private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private InstituicaoRepositorio instituicaoRepositorio;
	

	public Optional<List<Instituicao>> BuscarTodos() {
		log.info("Buscando todos os institutos");
		return Optional.ofNullable(instituicaoRepositorio.findAll());
	}
	
	public Optional<Instituicao> BuscarPorNomeInstituicao(String nome) {
		log.info("Buscando Instituicao pelo nome {} ",nome);
		return Optional.ofNullable(instituicaoRepositorio.findByNome(nome));
	}

	public Optional<Instituicao> BuscarPorId(Long id) {
		log.info("Buscando Instituicao pelo id {} ",id);
		return Optional.ofNullable(instituicaoRepositorio.findOne(id));
	}

	public Instituicao Salvar(Instituicao instituicao) {
		log.info("Buscando Instituicao pelo codigo {} ",instituicao.getNome());
		return instituicaoRepositorio.save(instituicao);
	}
	public void Deletar(Long id) {
		log.info("Deletando a  Instituicao com id: {}", id);
		instituicaoRepositorio.delete(id);
	}
}
