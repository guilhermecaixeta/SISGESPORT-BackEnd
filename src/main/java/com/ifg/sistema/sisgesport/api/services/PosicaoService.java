package com.ifg.sistema.sisgesport.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ifg.sistema.sisgesport.api.entities.Posicao;

public interface PosicaoService {
	
	Optional<Posicao> BuscarPorId(Long id);
	Optional<Posicao> BuscarPorNome(String nome);
	Optional<List<Posicao>> BuscarPorModalidadeId(Long id_modalidade);
	Optional<Page<Posicao>> BuscarPorModalidadeId(Long id_modalidade, Pageable page);
	Posicao Salvar(Posicao salvar);
}
