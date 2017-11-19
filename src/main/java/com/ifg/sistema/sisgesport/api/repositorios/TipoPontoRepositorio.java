package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Tipo_Ponto;

public interface TipoPontoRepositorio extends JpaRepository<Tipo_Ponto, Long> {

	@Transactional(readOnly=true)
	Tipo_Ponto findById(Integer id);
	
	@Transactional(readOnly=true)
	List<Tipo_Ponto> findByModalidadeId(Integer id);
}
