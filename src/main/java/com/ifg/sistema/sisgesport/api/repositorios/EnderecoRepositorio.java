package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Endereco;
@Transactional(readOnly=true)
public interface EnderecoRepositorio extends JpaRepository<Endereco, Long> {

	List<Endereco> findByLogradouroBairroMunicipioCepmunicipioIsAndLogradouroBairroCepbairroIsAndLogradouroCeplogradouro
	(String cepmunicipio, String cepbairro, String ceplogradouro);
	
	Endereco findById(Integer id);
}
