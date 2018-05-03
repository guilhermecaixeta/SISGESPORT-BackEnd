package com.ifg.sistema.sisgesport.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import com.ifg.sistema.sisgesport.api.entities.Logradouro;;

@Transactional(readOnly=true)
public interface LogradouroRepositorio extends JpaRepository<Logradouro, Long> {

	Logradouro findByCepLogradouro(String cepLogradouro);
	
	Logradouro findByBairroMunicipioCepMunicipioOrBairroCepBairroOrCepLogradouro(String cepmunicipio, String cepbairro, String ceplogradouro);
}
