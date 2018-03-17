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
	
	@Override
	public Optional<Aluno> BuscarPorMatricula(String matricula){
		log.info("realizando a busca por matrícula {}", matricula);
		return Optional.ofNullable(alunoRepositorio.findByMatricula(matricula));
	}
	
	@Override
	public Optional<List<Aluno>> BuscarPorIdTurma(Integer id){
		log.info("realizando a busca pelo id da turma {}", id);
		return Optional.ofNullable(alunoRepositorio.findByTurmaId(id));
	}
	
	@Override
	public Optional<Page<Aluno>> BuscarPorIdTurmaPagination(Integer id, Pageable pageable){
		log.info("realizando a busca por matrícula {}", id);
		return Optional.ofNullable(alunoRepositorio.findByTurmaId(id, pageable));
	}
	
	@Override
	public Optional<List<Aluno>> BuscarPorIdEquipe(Integer id){
		log.info("realizando a busca pelo id da turma {}", id);
		return Optional.ofNullable(alunoRepositorio.findByEquipeId(id));
	}
	
	@Override
	public Optional<Page<Aluno>> BuscarPorIdEquipePagination(Integer id, Pageable pageable){
		log.info("realizando a busca por matrícula {}", id);
		return Optional.ofNullable(alunoRepositorio.findByEquipeId(id, pageable));
	}
	
	@Override
	public Aluno Salvar(Aluno aluno){
		log.info("Salvando dado: {}", aluno);
		return alunoRepositorio.save(aluno);
	}
	
	@Override
	public void Deletar(Aluno aluno) {
		log.info("Deletando dado que possui o id: {}", aluno.getId());
		alunoRepositorio.delete(aluno);
	}
}
