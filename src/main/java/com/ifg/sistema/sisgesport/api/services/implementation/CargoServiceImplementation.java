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

import com.ifg.sistema.sisgesport.api.entities.Cargo;
import com.ifg.sistema.sisgesport.api.repositorios.CargoRepositorio;
import com.ifg.sistema.sisgesport.api.services.CargoService;
@Service
public class CargoServiceImplementation implements CargoService {
	private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private CargoRepositorio cargoRepositorio;
	@Cacheable("BuscarDadosCacheCargo")
	public Optional<List<Cargo>> BuscarPorNome(String nome){
		log.info("realizando a busca por matrícula {}", nome);
		return Optional.ofNullable(cargoRepositorio.findByNomeContains(nome));
	}
	public Optional<Cargo> BuscarPorId(Long id){
		log.info("realizando a busca por matrícula {}", id);
		return Optional.ofNullable(cargoRepositorio.findOne(id));
	}
	@Cacheable("BuscarDadosCacheCargo")
	public Optional<List<Cargo>> BuscarPorInstituicaoId(Long id_instituicao){
		log.info("Buscando cargos pelo id da intituicao {}", id_instituicao);
		return Optional.ofNullable(cargoRepositorio.findByInstituicaoId(id_instituicao));
	}
	@Cacheable("BuscarDadosCacheCargo")
	public Page<Cargo> BuscarPorInstituicaoIdPaginavel(Long id_instituicao, PageRequest pageRequest){
		log.info("Buscando cargos pelo id da intituicao {}", id_instituicao);
		return cargoRepositorio.findByInstituicaoId(id_instituicao, pageRequest);
	}
	public Cargo Salvar(Cargo cargo){
		log.info("Salvando um novo cargo: {}", cargo.getNome());
		return cargoRepositorio.save(cargo);
	}
	public void Deletar(Long id) {
		log.info("Deletando o cargo com id: {}", id);
		cargoRepositorio.delete(id);
	}
}
