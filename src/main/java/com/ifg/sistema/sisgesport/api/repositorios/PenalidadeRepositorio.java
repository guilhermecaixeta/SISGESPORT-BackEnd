package com.ifg.sistema.sisgesport.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Penalidade;

public interface PenalidadeRepositorio extends JpaRepository<Penalidade, Long> {

	@Transactional(readOnly=true)
	Penalidade findById(Integer id);
	
	@Transactional(readOnly=true)
	Penalidade findByModalidadeId(Integer id);
}
