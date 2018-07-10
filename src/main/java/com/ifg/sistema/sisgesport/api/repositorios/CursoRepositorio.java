package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Curso;
@Transactional(readOnly=true)
public interface CursoRepositorio extends JpaRepository<Curso, Long> {
	
	List<Curso> findByInstituicaoId(Long id_instituicao);
	
	Page<Curso> findByInstituicaoId(Long id_instituicao, Pageable page);
}
