package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Imagem;
@Transactional(readOnly=true)
public interface ImagemRepositorio extends JpaRepository<Imagem, Long>{
	
	List<Imagem> findByInformacaoEventoId(Long id_informacao_evento);
	List<Imagem> findByEntidadeComumId(Long id_entidade_comum);
}
