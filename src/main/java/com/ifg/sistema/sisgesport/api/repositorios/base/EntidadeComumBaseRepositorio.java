package com.ifg.sistema.sisgesport.api.repositorios.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.ifg.sistema.sisgesport.api.entities.commom_entities.EntidadeComum;

@NoRepositoryBean
public interface EntidadeComumBaseRepositorio<T extends EntidadeComum> extends JpaRepository<T, Long>{}
