package com.ifg.sistema.sisgesport.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Bairro;

public interface BairroRepositorio extends JpaRepository<Bairro, Long> {

	@Transactional(readOnly=true)
	Bairro findByCepbairro(String cepbairro);
	
	@Transactional(readOnly=true)
	Bairro findByNome(String nome);
}
