package com.ifg.sistema.sisgesport.api.controller.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ifg.sistema.sisgesport.api.mapping.mappingEntitys;

public abstract class baseController<S, D> {
	protected static final Logger log = LoggerFactory.getLogger(baseController.class);

	protected mappingEntitys<S, D> mappingDTOToEntity;
	protected mappingEntitys<D, S> mappingEntityToDTO;
}
