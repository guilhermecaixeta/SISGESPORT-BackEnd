package com.ifg.sistema.sisgesport.api.extesion;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.ifg.sistema.sisgesport.api.entities.Endereco;
import com.ifg.sistema.sisgesport.api.services.BairroService;
import com.ifg.sistema.sisgesport.api.services.EnderecoService;
import com.ifg.sistema.sisgesport.api.services.LogradouroService;
import com.ifg.sistema.sisgesport.api.services.MunicipioService;

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
	@Autowired
	private EnderecoService eS;
	@Autowired
	private MunicipioService mS;
	@Autowired
	private BairroService bS;
	@Autowired
	private LogradouroService lS;

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
					Field field = entity.getClass().getDeclaredField("id");
					field.set(entity, (long) 0);
				} catch (NoSuchFieldException | SecurityException | IllegalArgumentException
						| IllegalAccessException e) {
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
	 * os itens passados na lista de excessões
	 * 
	 * @param source
	 *            Fonte
	 * @param destiny
	 *            Destino
	 * @param listExceptions
	 *            Lista de excessões
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
	 * mesmo se ela tiver herança.
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

	/**
	 * Salva uma lista de enderecos, salvando municipio, bairro e logradouro caso
	 * não cadastrados.
	 * 
	 * @param lista
	 *            lista de Enderecos
	 * @return
	 */
	public List<Endereco> saveListAdress(List<Endereco> lista) {
		lista.forEach(endereco -> {
			if (endereco.getLogradouro().getBairro().getMunicipio().getId() > 0)
				endereco.getLogradouro().getBairro()
						.setMunicipio(this.mS.Salvar(endereco.getLogradouro().getBairro().getMunicipio()));
			if (endereco.getLogradouro().getBairro().getId() > 0)
				endereco.getLogradouro().setBairro(this.bS.Salvar(endereco.getLogradouro().getBairro()));
			if (endereco.getLogradouro().getId() > 0)
				endereco.setLogradouro(this.lS.Salvar(endereco.getLogradouro()));
			endereco = this.eS.Salvar(endereco);
		});
		return lista;
	}
}
