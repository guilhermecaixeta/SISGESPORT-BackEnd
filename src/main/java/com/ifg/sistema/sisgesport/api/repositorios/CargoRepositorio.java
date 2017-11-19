package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Cargo;

public interface CargoRepositorio extends JpaRepository<Cargo, Long> {

	@Transactional(readOnly=true)
	List<Cargo> findByNomeContains(String nome);
	
	@Transactional(readOnly=true)
	Cargo findById(Integer id);
	
	@Transactional(readOnly=true)
	List<Cargo> findByInstituicaoId(Integer id);
	
	@Transactional(readOnly=true)
	Page<Cargo> findByInstituicaoId(Integer id, Pageable pageable);
}
