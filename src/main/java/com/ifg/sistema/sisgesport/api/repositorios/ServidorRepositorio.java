package com.ifg.sistema.sisgesport.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Servidor;;

public interface ServidorRepositorio extends JpaRepository<Servidor, Long> {

	@Transactional(readOnly=true)
	Servidor findByMatriculasiap(String matriculasiap);
}
