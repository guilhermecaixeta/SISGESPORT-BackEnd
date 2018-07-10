package com.ifg.sistema.sisgesport.api.repositorios;

import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Instituicao;
import com.ifg.sistema.sisgesport.api.repositorios.base.EntidadeComumBaseRepositorio;

public interface InstituicaoRepositorio extends EntidadeComumBaseRepositorio<Instituicao> {

	@Transactional(readOnly=true)
	Instituicao findByNome(String nome);
}
