package com.ifg.sistema.sisgesport.api.controller.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.ifg.sistema.sisgesport.api.mapping.mappingEntitys;
import com.ifg.sistema.sisgesport.api.response.Response;
/**
 * Controller base generica
 * @author guilh
 *
 * @param <S> Source
 * @param <D> Destiny
 * @param <ES> EntityService
 */
public abstract class baseController<S, D, ES> {
	protected Response<S> response = new Response<S>();
	protected Response<Page<S>> responsePage = new Response<Page<S>>();
	protected static final Logger log = LoggerFactory.getLogger(baseController.class);

	protected mappingEntitys<S, D> mappingDTOToEntity;
	protected mappingEntitys<D, S> mappingEntityToDTO;

	@Autowired
	protected ES entityService;
}
