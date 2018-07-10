package com.ifg.sistema.sisgesport.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Modalidade;
@Transactional(readOnly=true)
public interface ModalidadeRepositorio extends JpaRepository<Modalidade, Long> {
	
	Modalidade findByNome(String nome);
}
