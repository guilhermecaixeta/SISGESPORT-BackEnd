package com.ifg.sistema.sisgesport.api.mapping;

import org.modelmapper.ModelMapper;

public class mappingEntitys<S, D>{
	final Class<D> destiny;
	final Class<S> source;
		
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
 * @param entitySource
 * @return entityD
 */
		public D AsGenericMapping(S entitySource){
			ModelMapper mapper = new ModelMapper();
			D destObject =  
			    mapper.map(entitySource, getDestiny());
			return destObject;
		}
}
