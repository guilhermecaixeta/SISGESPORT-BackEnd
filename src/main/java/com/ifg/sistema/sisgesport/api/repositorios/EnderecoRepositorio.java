package com.ifg.sistema.sisgesport.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Endereco;

public interface EnderecoRepositorio extends JpaRepository<Endereco, Long> {

	@Transactional(readOnly=true)
	Endereco findByLogradouroBairroMunicipioCepmunicipioIsAndLogradouroBairroCepbairroIsAndLogradouroCeplogradouro
	(String cepmunicipio, String cepbairro, String ceplogradouro);
}
