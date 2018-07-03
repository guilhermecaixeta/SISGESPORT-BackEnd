package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.commom_entities.Pessoa;
import com.ifg.sistema.sisgesport.api.repositorios.UsuarioRepositorio;
import com.ifg.sistema.sisgesport.api.services.UsuarioService;
@Service
public class UsuarioServiceImplementation implements UsuarioService {
private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private UsuarioRepositorio pessoaRepositorio;
	
	public Optional<Pessoa> BuscarPorMatricula(String matricula){
		log.info("Realizando a busca pela matrícula {}", matricula);
		return Optional.ofNullable(pessoaRepositorio.findByMatricula(matricula));
	}
}
