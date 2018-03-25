package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.Logradouro;
import com.ifg.sistema.sisgesport.api.repositorios.LogradouroRepositorio;
import com.ifg.sistema.sisgesport.api.services.LogradouroService;
@Service
public class LogradouroServiceImplementation implements LogradouroService {
private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private LogradouroRepositorio logradouroRepositorio;
	public Optional<Logradouro> BuscarPorId(Long id) {
		log.info("Buscando logradouro pelo nome {} ", id);
		return Optional.ofNullable(logradouroRepositorio.findOne(id));
	}

	public Optional<Logradouro> BuscarPorCepLogradouro(String cepLogradouro) {
		log.info("Buscando logradouro pelo dep do logradouro {} ", cepLogradouro);
		return Optional.ofNullable(logradouroRepositorio.findByCepLogradouro(cepLogradouro));
	}

	public Optional<Logradouro> BuscarPorCepCompleto(String cepmunicipio, String cepbairro, String ceplogradouro) {
		log.info("Buscando logradouro pelo cep completo {1}{2}-{3} ", cepmunicipio, cepbairro, ceplogradouro);
		return Optional.ofNullable(logradouroRepositorio.findByBairroMunicipioCepMunicipioOrBairroCepbairroOrCepLogradouro(cepmunicipio, cepbairro, ceplogradouro));
	}

	public Logradouro Salvar(Logradouro logradouro) {
		log.info("Salvando um novo logradouro no banco de dados");
		return logradouroRepositorio.save(logradouro);
	}
	public void Deletar(Long id) {
		log.info("Deletando o logradouro com id: {}", id);
		logradouroRepositorio.delete(id);
	}
}
