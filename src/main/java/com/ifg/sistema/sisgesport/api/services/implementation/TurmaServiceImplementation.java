package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.Turma;
import com.ifg.sistema.sisgesport.api.repositorios.TurmaRepositorio;
import com.ifg.sistema.sisgesport.api.services.TurmaService;
@Service
public class TurmaServiceImplementation implements TurmaService {
private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private TurmaRepositorio turmaRepositorio;
	
	public Optional<Turma> BuscarPorId(Long id) {
		log.info("Buscando servidor pelo id {} ", id);
		return Optional.ofNullable(turmaRepositorio.findOne(id));
	}

	public Optional<Turma> BuscarPorNome(String nome) {
		log.info("Buscando Turma pelo nome {} ", nome);
		return Optional.ofNullable(turmaRepositorio.findByNome(nome));
	}

	public Optional<List<Turma>> BuscarPorCursoId(Long id_curso) {
		log.info("Buscando Turma pelo id curso {} ", id_curso);
		return Optional.ofNullable(turmaRepositorio.findByCursoId(id_curso));
	}

	public Page<Turma> BuscarPorCursoIdPaginavel(Long id_curso, PageRequest pageRequest) {
		log.info("Buscando Turma pelo id {} ", id_curso);
		return turmaRepositorio.findByCursoId(id_curso, pageRequest);
	}

	public Turma Salvar(Turma turma) {
		log.info("Buscando servidor pelo id {} ", turma);
		return turmaRepositorio.save(turma);
	}

}
