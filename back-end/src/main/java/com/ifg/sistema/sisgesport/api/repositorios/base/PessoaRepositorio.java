package com.ifg.sistema.sisgesport.api.repositorios.base;

import org.springframework.data.repository.NoRepositoryBean;
import com.ifg.sistema.sisgesport.api.entities.commom_entities.Pessoa;

@NoRepositoryBean
public interface PessoaRepositorio<T extends Pessoa> extends EntidadeComumRepositorio<T> {
	public T findByEmail(String email);

	public T findByNome(String nome);
	
	public T findByMatricula(String matricula);
}
