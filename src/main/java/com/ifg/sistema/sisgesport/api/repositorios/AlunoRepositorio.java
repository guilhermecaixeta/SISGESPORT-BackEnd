package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Aluno;
@Transactional(readOnly=true)
public interface AlunoRepositorio extends JpaRepository<Aluno, Long> {

	Aluno findByMatricula(String matricula);
	
	List<Aluno> findByTurmaId(int id_turma);
	
	Page<Aluno> findByTurmaId(int id_turma, Pageable pageable);
	
	List<Aluno> findByEquipeId(Integer id_equipe);
	
	Page<Aluno> findByEquipeId(Integer id_equipe, Pageable pageable);
	
}
