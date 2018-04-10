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
	
	Aluno findByEmail(String email);
	
	List<Aluno> findByTurmaId(Long id);
	
	Page<Aluno> findByTurmaId(Long id_turma, Pageable pageable);
	
	List<Aluno> findByEquipeId(Long id_equipe);
	
	Page<Aluno> findByEquipeId(Long id_equipe, Pageable pageable);
	
}
