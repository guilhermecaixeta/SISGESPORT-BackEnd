package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.Estado;
import com.ifg.sistema.sisgesport.api.repositorios.EstadoRepositorio;
import com.ifg.sistema.sisgesport.api.services.EstadoService;
@Service
public class EstadoServiceImplementation implements EstadoService {
	private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private EstadoRepositorio estadoRepositorio;
	public Optional<Estado> BuscarPorNomeOuUF(String nome, String UF){
		log.info("Buscando estado pelo nome: {1} ou UF: {2} ", nome, UF);
		return Optional.ofNullable(estadoRepositorio.findByNomeOrUf(nome, UF));
	}
	public Optional<Estado> BuscarPorId(Long id){
		log.info("Buscando estado pelo id {} ", id);
		return Optional.ofNullable(estadoRepositorio.findOne(id));
	}
	public Estado Salvar(Estado estado) {
		log.info("Salvando o estado: ", estado.getNome());
		return estadoRepositorio.save(estado);
	}
}
