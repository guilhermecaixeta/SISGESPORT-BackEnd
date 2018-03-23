package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Tipo_Ponto;
@Transactional(readOnly=true)
public interface TipoPontoRepositorio extends JpaRepository<Tipo_Ponto, Long> {

	Tipo_Ponto findByNome(String nome);
	
	List<Tipo_Ponto> findByModalidadeId(Long id_modalidade);
	
	Page<Tipo_Ponto> findByModalidadeId(Long id_modalidade, Pageable page);
}
