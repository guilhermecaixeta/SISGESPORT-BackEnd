package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Penalidade;
@Transactional(readOnly=true)
public interface PenalidadeRepositorio extends JpaRepository<Penalidade, Long> {
	
	Penalidade findByNome(String nome);
	
	List<Penalidade> findByModalidadeId(Long id);

	Page<Penalidade> findByModalidadeId(Long id_modalidade, Pageable page);
}
