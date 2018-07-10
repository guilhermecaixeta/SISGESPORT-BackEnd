package com.ifg.sistema.sisgesport.api.repositorios;

import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.commom_entities.Pessoa;
import com.ifg.sistema.sisgesport.api.repositorios.base.PessoaBaseRepositorio;
@Transactional(readOnly=true)
public interface UsuarioRepositorio extends PessoaBaseRepositorio<Pessoa> {

}
