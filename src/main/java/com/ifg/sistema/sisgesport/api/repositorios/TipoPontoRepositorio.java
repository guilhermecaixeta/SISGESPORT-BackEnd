package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.TipoPonto;
@Transactional(readOnly=true)
public interface TipoPontoRepositorio extends JpaRepository<TipoPonto, Long> {

	TipoPonto findByNome(String nome);
	
	List<TipoPonto> findByModalidadeId(Long id_modalidade);
	
	Page<TipoPonto> findByModalidadeId(Long id_modalidade, Pageable page);
}
