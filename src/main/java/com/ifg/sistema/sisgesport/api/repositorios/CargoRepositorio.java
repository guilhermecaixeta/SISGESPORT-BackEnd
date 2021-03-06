package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Cargo;
@Transactional(readOnly=true)
public interface CargoRepositorio extends JpaRepository<Cargo, Long> {

	List<Cargo> findByNomeContains(String nome);
		
	List<Cargo> findByInstituicaoId(Long id);
	
	Page<Cargo> findByInstituicaoId(Long id, Pageable pageable);
}
