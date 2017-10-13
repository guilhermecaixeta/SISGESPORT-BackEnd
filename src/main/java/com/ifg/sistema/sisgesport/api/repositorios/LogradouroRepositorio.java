package com.ifg.sistema.sisgesport.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Logradouro;;

public interface LogradouroRepositorio extends JpaRepository<Logradouro, Long> {

	@Transactional(readOnly=true)
	Logradouro findByCeplogradouro(String ceplogradouro);
}
