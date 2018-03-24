package com.ifg.sistema.sisgesport.api.services.implementation;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifg.sistema.sisgesport.api.entities.Imagem;
import com.ifg.sistema.sisgesport.api.repositorios.ImagemRepositorio;
import com.ifg.sistema.sisgesport.api.services.ImagemService;
@Service
public class ImagemServiceImplementation implements ImagemService {
private static final Logger log = LoggerFactory.getLogger(AlunoServiceImplementation.class);
	
	@Autowired
	private ImagemRepositorio imagemRepositorio;
	public Optional<List<Imagem>> BuscarPorEntidadeComumId(Long id_entidade_comum) {
		log.info("Buscando imagem pelo id da entidade comum {} ",id_entidade_comum);
		return Optional.ofNullable(imagemRepositorio.findByEntidadeComumId(id_entidade_comum));
	}

	public Optional<List<Imagem>> BuscarPorInformacaoEventoId(Long id_informacao_evento) {
		log.info("Buscando imagem pelo id da informacao evento {} ",id_informacao_evento);
		return Optional.ofNullable(imagemRepositorio.findByInformacaoEventoId(id_informacao_evento));
	}

	public Optional<Imagem> BuscarPorId(Long id) {
		log.info("Buscando imagem evento pelo id {} ",id);
		return Optional.ofNullable(imagemRepositorio.findOne(id));
	}

	public Imagem Salvar(Imagem imagem) {
		log.info("Salvando a imagem {} ",imagem.getNome());
		return imagemRepositorio.save(imagem);
	}

}
