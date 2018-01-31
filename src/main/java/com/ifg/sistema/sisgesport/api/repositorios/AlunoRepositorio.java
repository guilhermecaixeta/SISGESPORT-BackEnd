package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Aluno;

public interface AlunoRepositorio extends JpaRepository<Aluno, Long> {

	@Transactional(readOnly=true)
	Aluno findByMatricula(String matricula);
	
	@Transactional(readOnly=true)
	List<Aluno> findByTurmaId(int id_turma);
	
	@Transactional(readOnly=true)
	Page<Aluno> findByTurmaId(int id_turma, Pageable pageable);
	
	@Transactional(readOnly=true)
	List<Aluno> findByEquipeId(Integer id_equipe);
	
	@Transactional(readOnly=true)
	Page<Aluno> findByEquipeId(Integer id_equipe, Pageable pageable);
	
}
