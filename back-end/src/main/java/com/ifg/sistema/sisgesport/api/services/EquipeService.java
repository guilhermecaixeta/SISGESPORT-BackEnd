package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ifg.sistema.sisgesport.api.entities.Equipe;

public interface EquipeService {

	/**
	 * Busca as equipes pelo id do evento
	 * @param id
	 * @return Optional<Equipe>
	 */
	Optional<List<Equipe>> BuscarEquipePorIdEvento(Long id_evento);
	/**
	 * Busca as equipes pelo id do evento com paginacao
	 * @param id
	 * @return Optional<Equipe>
	 */
	Page<Equipe> BuscarEquipePorIdEventoPaginavel(Long id_evento, PageRequest pageRequest);
	/**
	 * Busca o curso pelo seu id
	 * @param id
	 * @return Optional<Equipe>
	 */
	Optional<Equipe> BuscarPorCodigoEquipe(String codigo);
	/**
	 * Busca o curso pelo seu id
	 * @param id
	 * @return Optional<Equipe>
	 */
	Optional<Equipe> BuscarPorId(Long id);
	/**
	 * Salva uma nova equipe no banco de dados
	 * @param equipe
	 * @return
	 */
	Equipe Salvar(Equipe equipe);
	void Deletar(Long id);
}
