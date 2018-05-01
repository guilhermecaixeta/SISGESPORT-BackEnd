package com.ifg.sistema.sisgesport.api.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Instituicao;

public interface InstituicaoRepositorio extends JpaRepository<Instituicao, Long> {

	@Transactional(readOnly=true)
	Instituicao findByNome(String nome);
}
