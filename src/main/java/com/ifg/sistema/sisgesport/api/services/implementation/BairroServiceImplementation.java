package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.Bairro;
import com.ifg.sistema.sisgesport.api.repositorios.BairroRepositorio;
import com.ifg.sistema.sisgesport.api.services.BairroService;
@Service
public class BairroServiceImplementation implements BairroService {
	private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private BairroRepositorio bairroRepositorio;
	
	public Optional<Bairro> BuscarPorCepBairro(String cepBairro) {
		log.info("Buscando Bairro pelo cep: {}", cepBairro);
		return Optional.ofNullable(bairroRepositorio.findByCepbairro(cepBairro));
	}
	
	public Optional<Bairro> BuscarPorNome(String nomeBairro) {
		log.info("Buscando Bairro pelo nome: {}", nomeBairro);
		return Optional.ofNullable(bairroRepositorio.findByNome(nomeBairro));
	}
	
	public Optional<Bairro> BuscarPorId(Long id){
		log.info("Buscando Bairro pelo id: {}", id);
		return Optional.ofNullable(bairroRepositorio.findOne(id));
	}
	
	public Bairro Salvar(Bairro bairro) {
		log.info("Salvando um novo Bairro: {}", bairro.getNome());
		return bairroRepositorio.save(bairro);
	}
	public void Deletar(Long id) {
		log.info("Deletando o bairro com id: {}", id);
		bairroRepositorio.delete(id);
	}
}
