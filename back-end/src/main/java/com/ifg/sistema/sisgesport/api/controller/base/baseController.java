package com.ifg.sistema.sisgesport.api.controller.base;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.ifg.sistema.sisgesport.api.extesion.Extension;
import com.ifg.sistema.sisgesport.api.response.Response;
/**
 * Controller base, é uma controller generica com metodos comuns a todas as controllers
 * @author guilherme caixeta
 *
 * @param <S> Source - Entidade Fonte
 * @param <D> Destiny - Entidade Destino
 * @param <ES> EntityService - Entidade de Servico
 */

public abstract class baseController<S, D, ES>{
	protected Response<S> response = new Response<S>();
	protected Response<Page<S>> responsePage = new Response<Page<S>>();
	protected Response<List<S>> responseList = new Response<List<S>>();
	protected static final Logger log = LoggerFactory.getLogger(baseController.class);
	
	protected Extension<S, D> mappingDTOToEntity;
	protected Extension<D, S> mappingEntityToDTO;

	@Autowired
	protected ES entityService;
}
