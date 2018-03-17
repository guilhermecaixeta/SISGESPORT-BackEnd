package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Tipo_Ponto;
@Transactional(readOnly=true)
public interface TipoPontoRepositorio extends JpaRepository<Tipo_Ponto, Long> {

	Tipo_Ponto findById(Integer id);
	
	List<Tipo_Ponto> findByModalidadeId(Integer id);
}
