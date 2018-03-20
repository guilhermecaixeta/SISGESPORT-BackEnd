package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.Aluno;
import com.ifg.sistema.sisgesport.api.repositorios.AlunoRepositorio;
import com.ifg.sistema.sisgesport.api.services.AlunoService;
@Service
public class AlunoServiceImplementation implements AlunoService{
	private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private AlunoRepositorio alunoRepositorio;
	
	public Optional<Aluno> BuscarPorMatricula(String matricula){
		log.info("realizando a busca por matrícula {}", matricula);
		return Optional.ofNullable(alunoRepositorio.findByMatricula(matricula));
	}
	
	public Optional<List<Aluno>> BuscarPorIdTurma(Long id_turma){
		log.info("realizando a busca pelo id da turma {}", id_turma);
		return Optional.ofNullable(alunoRepositorio.findByTurmaId(id_turma));
	}
	
	public Optional<Page<Aluno>> BuscarPorIdTurmaPaginavel(Long id_turma, Pageable pageable){
		log.info("realizando a busca por matrícula {}", id_turma);
		return Optional.ofNullable(alunoRepositorio.findByTurmaId(id_turma, pageable));
	}
	public Optional<List<Aluno>> BuscarPorIdEquipe(Long id_equipe){
		log.info("realizando a busca pelo id da turma {}", id_equipe);
		return Optional.ofNullable(alunoRepositorio.findByEquipeId(id_equipe));
	}
	
	public Optional<Page<Aluno>> BuscarPorIdEquipePaginavel(Long id_equipe, Pageable pageable){
		log.info("realizando a busca por matrícula {}", id_equipe);
		return Optional.ofNullable(alunoRepositorio.findByEquipeId(id_equipe, pageable));
	}
	
	public Optional<Aluno> BuscarPorId(Long id){
		log.info("Buscando o aluno pelo id {}", id);
		return Optional.ofNullable(alunoRepositorio.findOne(id));
	}
	public Aluno Salvar(Aluno aluno){
		log.info("Salvando dado: {}", aluno);
		return alunoRepositorio.save(aluno);
	}
}
