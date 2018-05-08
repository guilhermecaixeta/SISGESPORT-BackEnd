package com.ifg.sistema.sisgesport.api.repositorios;

import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Instituicao;
import com.ifg.sistema.sisgesport.api.repositorios.base.EntidadeComumRepositorio;

public interface InstituicaoRepositorio extends EntidadeComumRepositorio<Instituicao> {

	@Transactional(readOnly=true)
	Instituicao findByNome(String nome);
}
