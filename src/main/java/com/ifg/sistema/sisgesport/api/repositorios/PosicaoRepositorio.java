package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Posicao;
@Transactional(readOnly=true)
public interface PosicaoRepositorio extends JpaRepository<Posicao, Long> {

	Posicao findByNomeEquals(String nome);
	
	List<Posicao> findByModalidadeId(Long id_modalidade);
	
	Page<Posicao> findByModalidadeId(Long id_modalidade, Pageable page);
}
