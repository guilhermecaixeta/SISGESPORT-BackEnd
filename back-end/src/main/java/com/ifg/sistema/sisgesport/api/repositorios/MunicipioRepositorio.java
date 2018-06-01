package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Municipio;
@Transactional(readOnly=true)
public interface MunicipioRepositorio extends JpaRepository<Municipio, Long> {
	Municipio findByNome(String nome);
	List<Municipio> findByEstadoId(Long id_estado);
	Page<Municipio> findByEstadoId(Long id_estado, Pageable page);
}
