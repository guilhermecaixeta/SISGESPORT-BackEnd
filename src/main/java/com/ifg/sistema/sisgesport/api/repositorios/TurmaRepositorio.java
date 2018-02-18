package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Turma;

public interface TurmaRepositorio  extends JpaRepository<Turma, Long> {

	@Transactional(readOnly=true)
	Turma findById(Integer id);
	
	@Transactional(readOnly=true)
	List<Turma> findByCursoId(Integer id_curso);
	
	@Transactional(readOnly=true)
	Page<Turma> findByCursoId(Integer id_curso, Pageable page);
}
