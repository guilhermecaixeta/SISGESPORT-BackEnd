package com.ifg.sistema.sisgesport.api.extesion;

import java.lang.reflect.Field;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

public class Extension<S, D> {

	final Class<D> destiny;
	final Class<S> source;
	private ModelMapper mapper = new ModelMapper();
	
	public Extension(Class<S> source, Class<D> destiny) {
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
	/**
	 * Atualiza uma entidade D a partir de uma entidade S
	 * @param source
	 * @param destiny
	 * @return
	 * @throws Exception
	 */
	public D updateGeneric(S source, D destiny) throws Exception {
		Field[] fieldList = source.getClass().getDeclaredFields();
		for (Field classField : fieldList) {
			if (!classField.isAccessible()) {
				classField.setAccessible(true);
			}
			Field dField = destiny.getClass().getDeclaredField(classField.getName());
			if (!dField.isAccessible()) {
				dField.setAccessible(true);
			}
			if (classField.get(source) != null && !classField.get(source).equals(dField.get(destiny))) {
				dField.set(destiny, classField.get(source));
			}
		}
		return destiny;
	}
}
