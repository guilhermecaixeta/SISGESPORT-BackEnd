package com.ifg.sistema.sisgesport.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Estado;;

public interface EstadoRepositorio extends JpaRepository<Estado, Long> {

	@Transactional(readOnly=true)
	Estado findByNomeOrUf(String nome, String uf);
}
