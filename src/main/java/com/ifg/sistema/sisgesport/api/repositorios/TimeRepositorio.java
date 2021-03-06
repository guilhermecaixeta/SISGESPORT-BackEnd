package com.ifg.sistema.sisgesport.api.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ifg.sistema.sisgesport.api.entities.Time;
@Transactional(readOnly=true)
public interface TimeRepositorio extends JpaRepository<Time, Long> {

	Time findByEquipeCodigoEquipe(String codigo);
	
	List<Time> findByEquipeId(Long id_equipe);

	List<Time> findByEquipeEventoIdAndEventoModalidadeModalidadeId(Long id_evento, Long id_modalidade);
	
	Page<Time> findByEquipeId(Long id_equipe, Pageable page);

	Page<Time> findByEquipeEventoIdAndEventoModalidadeModalidadeIdAndEventoModalidadeSexo
			(Long id_evento, Long id_modalidade, char sexo, Pageable page);
}
