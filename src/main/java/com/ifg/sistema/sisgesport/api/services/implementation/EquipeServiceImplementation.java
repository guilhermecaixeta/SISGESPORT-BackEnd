package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.Equipe;
import com.ifg.sistema.sisgesport.api.repositorios.EquipeRepositorio;
import com.ifg.sistema.sisgesport.api.services.EquipeService;
@Service
public class EquipeServiceImplementation implements EquipeService {
	private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private EquipeRepositorio equipeRepositorio;
	@Cacheable("BuscarDadosCache")
	public Optional<List<Equipe>> BuscarEquipePorIdEvento(Long id_evento){
		log.info("Buscando equipe pelo id evento {}", id_evento);
		return Optional.ofNullable(equipeRepositorio.findByEventoId(id_evento));
	}
	@Cacheable("BuscarDadosCache")
	public Page<Equipe> BuscarEquipePorIdEventoPaginavel(Long id_evento, PageRequest pageRequest){
		log.info("Buscando equipe pelo id evento {}", id_evento);
		return equipeRepositorio.findByEventoId(id_evento, pageRequest);
	}
	public Optional<Equipe> BuscarPorCodigoEquipe(String codigo){
		log.info("Buscando equipe pelo código {}", codigo);
		return Optional.ofNullable(equipeRepositorio.findByCodigoEquipe(codigo));
	}
	public Optional<Equipe> BuscarPorMatriculaCapitao(String matricula){
		log.info("Buscando equipe pela matricula do capitão {}", matricula);
		return Optional.ofNullable(equipeRepositorio.findByCapitaoMatricula(matricula));
	}
	public Optional<Equipe> BuscarPorId(Long id){
		log.info("Buscando equipe pelo id {}", id);
		return Optional.ofNullable(equipeRepositorio.findOne(id));
	}
	public Equipe Salvar(Equipe equipe) {
		log.info("Salvando a equipe de código {}", equipe.getCodigoEquipe());
		return equipeRepositorio.save(equipe);
	}
	public void Deletar(Long id) {
		log.info("Deletando o equipe com id: {}", id);
		equipeRepositorio.delete(id);
	}
}
