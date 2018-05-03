package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Servidor;;
@Transactional(readOnly=true)
public interface ServidorRepositorio extends JpaRepository<Servidor, Long> {

	Servidor findByEmail(String email);
	Servidor findByMatriculaSiap(String matriculaSiap);	
	List<Servidor> findByCargoId(Long id);
	Page<Servidor> findByCargoId(Long id_cargo, Pageable pageable);
	Page<Servidor> findByCargoInstituicaoId(Long id_instituicao, Pageable pageable);
}
