package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.Modalidade;
import com.ifg.sistema.sisgesport.api.repositorios.ModalidadeRepositorio;
import com.ifg.sistema.sisgesport.api.services.ModalidadeService;
@Service
public class ModalidadeServiceImplementation implements ModalidadeService {
private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private ModalidadeRepositorio modalidadeRepositorio;
	public Optional<Modalidade> BuscarPorId(Long id) {
		log.info("Buscando logradouro pelo nome {} ", id);
		return Optional.ofNullable(modalidadeRepositorio.findOne(id));
	}
	public Optional<Modalidade> BuscarPorNome(String nome) {
		log.info("Buscando logradouro pelo nome {} ", nome);
		return Optional.ofNullable(modalidadeRepositorio.findByNome(nome));
	}
	public Page<Modalidade> BuscarTodosPaginavel(PageRequest pageRequest){
		log.info("Buscando todas as modalidades paginadas.");
		return modalidadeRepositorio.findAll(pageRequest);
	}
	public Optional<List<Modalidade>> BuscarTodos(){
		log.info("Buscando todas as modalidades");
		return Optional.ofNullable(modalidadeRepositorio.findAll());
	}
    @CachePut("BuscarDadosCacheModalidade")
	public Modalidade Salvar(Modalidade modalidade) {
		log.info("Salvando uma nova modalidade no banco de dados {} ", modalidade.getNome());
		return modalidadeRepositorio.save(modalidade);
	}
	public void Deletar(Long id) {
		log.info("Deletando o modalidade com id: {}", id);
		modalidadeRepositorio.delete(id);
	}
}
