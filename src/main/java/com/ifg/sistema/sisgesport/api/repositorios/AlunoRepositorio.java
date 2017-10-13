package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Aluno;

@Transactional(readOnly=true)
@NamedQueries({
	@NamedQuery(name="AlunoRepositorio.findByTimeId",
			query="SELECT aluno from Aluno aluno WHERE aluno.times.id = :timeId")})

public interface AlunoRepositorio extends JpaRepository<Aluno, Long> {

	@Transactional(readOnly=true)
	Aluno findByMatricula(String matricula);
	
	@Transactional(readOnly=true)
	List<Aluno> findByTurmaId(int id);
	
	@Transactional(readOnly=true)
	List<Aluno> findByEquipesId(Integer id);
	
	@Transactional(readOnly=true)
	Page<Aluno> findByEquipesId(Integer id, Pageable pageable);
	
}
