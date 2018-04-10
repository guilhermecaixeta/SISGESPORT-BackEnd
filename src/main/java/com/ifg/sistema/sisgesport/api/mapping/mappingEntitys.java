package com.ifg.sistema.sisgesport.api.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

public class mappingEntitys<S, D> {
	final Class<D> destiny;
	final Class<S> source;
	private ModelMapper mapper = new ModelMapper();

	public mappingEntitys(Class<S> source, Class<D> destiny) {
		this.destiny = destiny;
		this.source = source;
	}

	public Class<D> getDestiny() {
		return destiny;
	}

	public Class<S> getSource() {
		return source;
	}

	/**
	 * Converte um tipo S em um tipo D
	 * 
	 * @param entitySource
	 * @return entityD
	 */
	public D AsGenericMapping(S entitySource) {
		D destObject = mapper.map(entitySource, getDestiny());
		return destObject;
	}
	/**
	 * Converte uma Page<S> para uma Page<D>
	 * @param entitySourcePageList
	 * @return Page<D>
	 */
	public Page<D> AsGenericMappingListPage(Page<S> entitySourcePageList) {
		return entitySourcePageList.map(entity -> this.AsGenericMapping(entity));
	}
}
