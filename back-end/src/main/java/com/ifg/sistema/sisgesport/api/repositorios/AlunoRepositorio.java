package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Aluno;
import com.ifg.sistema.sisgesport.api.repositorios.base.PessoaBaseRepositorio;
@Transactional(readOnly=true)
public interface AlunoRepositorio extends PessoaBaseRepositorio<Aluno>{
		
	List<Aluno> findByTurmaId(Long id_turma);
	
	Page<Aluno> findByTurmaId(Long id_turma, Pageable pageable);
	
	List<Aluno> findByEquipeId(Long id_equipe);
	
	Page<Aluno> findByEquipeId(Long id_equipe, Pageable pageable);
	
}
