package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.Modalidade;
import com.ifg.sistema.sisgesport.api.repositorios.ModalidadeRepositorio;
import com.ifg.sistema.sisgesport.api.services.ModalidadeService;
@Service
public class ModalidadeServiceImplementation implements ModalidadeService {
private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private ModalidadeRepositorio modalidadeRepositorio;
	public Optional<Modalidade> BuscarPorId(Long id) {
		log.info("Buscando logradouro pelo nome {} ", id);
		return Optional.ofNullable(modalidadeRepositorio.findOne(id));
	}

	public Optional<Modalidade> BuscarPorNome(String nome) {
		log.info("Buscando logradouro pelo nome {} ", nome);
		return Optional.ofNullable(modalidadeRepositorio.findByNome(nome));
	}

	public Modalidade Salvar(Modalidade modalidade) {
		log.info("Salvando uma nova modalidade no banco de dados {} ", modalidade.getNome());
		return modalidadeRepositorio.save(modalidade);
	}

}
