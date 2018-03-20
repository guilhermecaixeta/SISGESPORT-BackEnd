package com.ifg.sistema.sisgesport.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Bairro;
@Transactional(readOnly=true)
public interface BairroRepositorio extends JpaRepository<Bairro, Long> {

	Bairro findByCepbairro(String cepBairro);
	
	Bairro findByNome(String nome);
}
