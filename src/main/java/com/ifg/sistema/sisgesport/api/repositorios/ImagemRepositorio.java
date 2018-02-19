package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Imagem;

public interface ImagemRepositorio extends JpaRepository<Imagem, Long>{

	@Transactional(readOnly=true)
	Imagem findById(Integer id);
	
	@Transactional(readOnly=true)
	List<Imagem> findByInformacaoEventoId(Integer id_informacao_evento);
}
