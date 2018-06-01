package com.ifg.sistema.sisgesport.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Endereco;
@Transactional(readOnly=true)
public interface EnderecoRepositorio extends JpaRepository<Endereco, Long> {
		
	Endereco findByCep(String cep);
}
