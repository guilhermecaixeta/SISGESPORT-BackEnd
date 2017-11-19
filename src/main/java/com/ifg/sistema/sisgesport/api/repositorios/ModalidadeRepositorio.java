package com.ifg.sistema.sisgesport.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Modalidade;

public interface ModalidadeRepositorio extends JpaRepository<Modalidade, Long> {

	@Transactional(readOnly=true)
	Modalidade findById(Integer id);
	
	@Transactional(readOnly=true)
	Modalidade findByNome(String nome);
}
