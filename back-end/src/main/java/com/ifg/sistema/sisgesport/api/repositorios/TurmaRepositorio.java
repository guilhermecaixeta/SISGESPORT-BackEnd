package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Turma;
@Transactional(readOnly=true)
public interface TurmaRepositorio  extends JpaRepository<Turma, Long> {

	Turma findByNomeEquals(String nome);
	
	List<Turma> findByCursoId(Long id_curso);
	
	Page<Turma> findByCursoId(Long id_curso, Pageable page);
}
