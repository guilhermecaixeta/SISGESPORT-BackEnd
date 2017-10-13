package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Cargo;

public interface CargoRepositorio extends JpaRepository<Cargo, Long> {

	@Transactional(readOnly=true)
	Cargo findByNome(String nome);
	
	@Transactional(readOnly=true)
	List<Cargo> findByInstituicaoId(Integer id);
}
