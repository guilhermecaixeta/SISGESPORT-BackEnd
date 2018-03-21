package com.ifg.sistema.sisgesport.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Municipio;
@Transactional(readOnly=true)
public interface MunicipioRepositorio extends JpaRepository<Municipio, Long> {

	Municipio findByCepMunicipio(String cepMunicipio);
	
	Municipio findByNomeOrSigla(String nome, String sigla);
}
