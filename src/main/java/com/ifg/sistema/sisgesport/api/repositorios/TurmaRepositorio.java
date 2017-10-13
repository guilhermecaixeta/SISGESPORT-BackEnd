package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Turma;

public interface TurmaRepositorio  extends JpaRepository<Turma, Long> {

	@Transactional(readOnly=true)
	Turma findByNome(String nome);
	
	@Transactional(readOnly=true)
	List<Turma> findByCursoId(int id);
}
