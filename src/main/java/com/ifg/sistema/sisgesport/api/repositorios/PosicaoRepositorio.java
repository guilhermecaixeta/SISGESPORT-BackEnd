package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Posicao;

public interface PosicaoRepositorio extends JpaRepository<Posicao, Long> {

	@Transactional(readOnly=true)
	Posicao findById(Integer id);
	
	@Transactional(readOnly=true)
	List<Posicao> findByModalidadeId(Integer id_modalidade);
	
	@Transactional(readOnly=true)
	Page<Posicao> findByModalidadeId(Integer id_modalidade, Pageable page);
}
