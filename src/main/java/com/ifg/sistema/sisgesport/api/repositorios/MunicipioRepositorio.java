package com.ifg.sistema.sisgesport.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Municipio;

public interface MunicipioRepositorio extends JpaRepository<Municipio, Long> {

	@Transactional(readOnly=true)
	Municipio findByCepmunicipio(String cepmunicipio);
	
	@Transactional(readOnly=true)
	Municipio findByNomeOrSigla(String nome, String sigla);
}
