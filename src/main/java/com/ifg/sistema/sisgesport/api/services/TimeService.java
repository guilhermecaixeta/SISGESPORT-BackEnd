package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.ifg.sistema.sisgesport.api.entities.Time;

public interface TimeService {
	/**
	 * Busca um time pelo id
	 * @param id
	 * @return
	 */
	Optional<Time> BuscarPorId(Long id);
	/**
	 * Busca um time pelo codigo equipe
	 * @param codigo
	 * @return
	 */
	Optional<Time> BuscarPorEquipeCodigoEquipe(String codigo);
	/**
	 * Busca uma lista de times pelo id equipe
	 * @param id_equipe
	 * @return
	 */
	Optional<List<Time>> BuscarPorEquipeId(Long id_equipe);
	/**
	 * Busca uma lista paginada de times pelo id equipe 
	 * @param id_equipe
	 * @param page
	 * @return
	 */
	Page<Time> BuscarPorEquipeIdPaginavel(Long id_equipe, PageRequest pageRequest);
	/**
	 * Salva um novo time no banco de dados
	 * @param time
	 * @return
	 */
	Time Salvar(Time time);
	void Deletar(Long id);
}
