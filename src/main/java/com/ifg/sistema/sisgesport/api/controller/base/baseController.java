package com.ifg.sistema.sisgesport.api.controller.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ifg.sistema.sisgesport.api.entities.PageConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.ifg.sistema.sisgesport.api.extesion.Extension;
import com.ifg.sistema.sisgesport.api.response.Response;

/**
 * Controller base, é uma controller generica com metodos comuns a todas as
 * controllers
 * 
 * @author guilherme caixeta
 *
 * @param <S>
 *            Source - Entidade Fonte
 * @param <D>
 *            Destiny - Entidade Destino
 * @param <ES>
 *            EntityService - Entidade de Servico
 */

public abstract class baseController<S, D, ES> {
	protected Response<S> response = new Response<S>();
	protected Response<Page<S>> responsePage = new Response<Page<S>>();
	protected Response<List<S>> responseList = new Response<List<S>>();

	protected static final Logger log = LoggerFactory.getLogger(baseController.class);

	protected Extension<S, D> mappingDTOToEntity;
	protected Extension<D, S> mappingEntityToDTO;

	@Autowired
	protected ES entityService;
    /**
     * Lista usada para exceções no mapeamento
     */
    protected List<String> listaExcecao = new ArrayList<String>();
	/**
	 * Instancia da Entidade destino para persistencia no banco de dados
	 */
	protected D entity;
	/**
	 * Instancia da Entidade Opcional destino
	 */
	protected Optional<D> entityOptional;
	/*
	 * Instancia da Entidade DTO para retorno dos métodos
	 */
	protected S entityDTO;
	/**
	 * Instancia da Entidade Opcional destino
	 */
	protected List<S> entityListDTO;
    /**
     * Instancia base para a paginação
     */
    protected  Page<S> entityPageListDTO;

}
