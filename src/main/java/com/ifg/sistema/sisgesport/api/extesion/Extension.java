package com.ifg.sistema.sisgesport.api.extesion;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

/**
 * Classe de metodos comuns
 * 
 * @author guilherme
 *
 * @param <S>
 *            - fonte
 * @param <D>
 *            - destino
 */
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
	 * Realiza o mapeamento de um tipo S em um tipo D
	 * 
	 * @param entitySource
	 *            Tipo Fonte (S - Source)
	 * @return entityD - Tipo Destino (D - Destiny)
	 */
	public D AsGenericMapping(S entitySource) {
		D destObject = mapper.map(entitySource, getDestiny());
		return destObject;
	}

	/**
	 * Converte uma Page<S> para uma Page<D>
	 * 
	 * @param entitySourcePageList
	 *            Lista paginada de entidade fonte
	 * @return Page<D>
	 */
	public Page<D> AsGenericMappingListPage(Page<S> entitySourcePageList) {
		return entitySourcePageList.map(entity -> this.AsGenericMapping(entity));
	}

	/**
	 * Converte uma List<S> para uma List<D>
	 * 
	 * @param entitySourcePageList
	 *            Lista paginada de entidade fonte
	 * @return Page<D>
	 */
	public List<D> AsGenericMappingList(List<S> entitySourceList, boolean clearId) {
		List<D> listMapped = new ArrayList<D>();
		entitySourceList.forEach(entity -> {
			if (clearId) {
				try {
					Field field = getFieldByName("id", getAllFields(new ArrayList<Field>(), entity.getClass()));
					field.set(entity, (long) 0);
				} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			listMapped.add(this.AsGenericMapping(entity));
		});
		return listMapped;
	}

	/**
	 * Atualiza uma entidade D(destino) a partir de uma entidade S(fonte), ignorando
	 * os itens passados na lista de exceções
	 * 
	 * @param source
	 *            Fonte
	 * @param destiny
	 *            Destino
	 * @param listExceptions
	 *            Lista de exceções
	 * @return
	 * @throws Exception
	 */
	public D updateGeneric(S source, D destiny, List<String> listExceptions) throws Exception {
		List<Field> fieldListSource = getAllFields(new ArrayList<Field>(), getSource());
		List<Field> fieldListDestiny = getAllFields(new ArrayList<Field>(), getDestiny());
		for (int i = 0; i < fieldListSource.size(); i++) {
			Field sourceF = fieldListSource.get(i);
			Field destinyF = getFieldByName(sourceF.getName(), fieldListDestiny);

			if (!sourceF.isAccessible()) {
				sourceF.setAccessible(true);
			}
			if (!destinyF.isAccessible()) {
				destinyF.setAccessible(true);
			}
			if (sourceF.get(source) != null && sourceF.get(source) != destinyF.get(destiny)
					&& !listExceptions.contains(sourceF.getName())) {
				destinyF.set(destiny, sourceF.get(source));
			}
		}

		return destiny;
	}

	/**
	 * Retorna o campo de um atributo a partir do nome, percorre um vetor de valores
	 * pesquisando o atributo com o valor que foi passado.
	 * 
	 * @param name
	 *            Nome de campo
	 * @param fields
	 *            Array de campos
	 * @return
	 */
	public Field getFieldByName(String name, List<Field> fields) {
		Field retorno = null;
		for (Field field : fields) {
			if (name == field.getName()) {
				retorno = field;
			}
		}
		return retorno;
	}

	/**
	 * Percorre uma entidade recursivamente retornando todos os campos da mesma,
	 * mesmo se ela possuir herança.
	 * 
	 * @param fields
	 * @param type
	 * @return
	 */
	public List<Field> getAllFields(List<Field> fields, Class<?> type) {
		fields.addAll(Arrays.asList(type.getDeclaredFields()));

		if (type.getSuperclass() != null) {
			getAllFields(fields, type.getSuperclass());
		}

		return fields;
	}
}
