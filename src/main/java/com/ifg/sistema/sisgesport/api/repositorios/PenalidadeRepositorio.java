package com.ifg.sistema.sisgesport.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Penalidade;
@Transactional(readOnly=true)
public interface PenalidadeRepositorio extends JpaRepository<Penalidade, Long> {
	
	Penalidade findByNome(String nome);
	
	Penalidade findByModalidadeId(Long id);
}
