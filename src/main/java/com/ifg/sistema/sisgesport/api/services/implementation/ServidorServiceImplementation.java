package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.Servidor;
import com.ifg.sistema.sisgesport.api.repositorios.ServidorRepositorio;
import com.ifg.sistema.sisgesport.api.services.ServidorService;

@Service
public class ServidorServiceImplementation implements ServidorService {
private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private ServidorRepositorio servidorRepositorio;
	
	public Optional<Servidor> BuscarPorId(Long id) {
		log.info("Buscando servidor pelo id {} ", id);
		return Optional.ofNullable(servidorRepositorio.findOne(id));
	}

	public Optional<Servidor> findByMatriculaSiap(String matriculaSiap) {
		log.info("Buscando servidor pela matricula siap {} ", matriculaSiap);
		return Optional.ofNullable(servidorRepositorio.findByMatriculaSiap(matriculaSiap));
	}

	public Optional<List<Servidor>> findByCargoId(Long id_instituicao) {
		log.info("Buscando servidor pelo id instituicao {} ", id_instituicao);
		return Optional.ofNullable(servidorRepositorio.findByCargoId(id_instituicao));
	}

	public Page<Servidor> findByCargoInstituicaoId(Long id_instituicao, PageRequest pageRequest) {
		log.info("Buscando servidor pelo id instituicao {} ", id_instituicao);
		return servidorRepositorio.findByCargoInstituicaoId(id_instituicao, pageRequest);
	}

	public Servidor Salvar(Servidor servidor) {
		log.info("Salvando um novo servidor no banco de dados {} ", servidor);
		return servidorRepositorio.save(servidor);
	}
	public void Deletar(Long id) {
		log.info("Deletando o servidor com id: {}", id);
		servidorRepositorio.delete(id);
	}
}
