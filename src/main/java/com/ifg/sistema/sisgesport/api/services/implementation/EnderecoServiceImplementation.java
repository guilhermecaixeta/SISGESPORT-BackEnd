package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.Endereco;
import com.ifg.sistema.sisgesport.api.repositorios.EnderecoRepositorio;
import com.ifg.sistema.sisgesport.api.services.EnderecoService;
@Service
public class EnderecoServiceImplementation implements EnderecoService {
	private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private EnderecoRepositorio enderecoRepositorio;
	
	public Optional<Endereco> BuscarPorId(Long id){
		log.info("realizando a busca por id {}", id);
		return Optional.ofNullable(enderecoRepositorio.findOne(id));
	}
	public Endereco Salvar(Endereco endereco) {
		log.info("Salvando o endereco {}", endereco.getComplemento());
		return enderecoRepositorio.save(endereco);
	}
}
