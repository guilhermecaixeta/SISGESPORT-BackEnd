package com.ifg.sistema.sisgesport.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ifg.sistema.sisgesport.api.entities.Jogador;
import com.ifg.sistema.sisgesport.api.repositorios.JogadorRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class JogadorServiceTeste {
	@MockBean
	private JogadorRepositorio jR;
	@Autowired
	private JogadorService jS;
	private static final Long id = (long) 1;
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.jR.save(Mockito.any(Jogador.class))).willReturn(new Jogador());
		BDDMockito.given(this.jR.findOne(Mockito.anyLong())).willReturn(new Jogador());
		BDDMockito.given(this.jR.findByTimeEquipeId(Mockito.anyLong())).willReturn(new ArrayList<Jogador>());
		BDDMockito.given(this.jR.findByTimeEquipeId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
		.willReturn(new PageImpl<Jogador>(new ArrayList<Jogador>()));
		BDDMockito.given(this.jR.findByTimeId(Mockito.anyLong())).willReturn(new ArrayList<Jogador>());
		BDDMockito.given(this.jR.findByTimeId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
		.willReturn(new PageImpl<Jogador>(new ArrayList<Jogador>()));
	}
	
	@Test
	public void TestPersistenciaJogador() {
		Jogador e = this.jS.Salvar(new Jogador());
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscaPorId() {
		Optional<Jogador> e = this.jS.BuscarPorId(id);
		assertNotNull(e);
	}
		
	@Test
	public void TestBuscarPorEquipeId() {
		Optional<List<Jogador>> c = this.jS.BuscarPorEquipeId(id);
		assertTrue(c.isPresent());
	}
	
	@Test
	public void TestBuscarPorEquipeIdPaginavel() {
		Page<Jogador> c = this.jS.BuscarPorEquipeIdPaginavel(id, new PageRequest(0,10));
		assertNotNull(c);
	}
	
	@Test
	public void TestBuscarPorTimeId() {
		Optional<List<Jogador>> c = this.jS.BuscarPorTimeId(id);
		assertTrue(c.isPresent());
	}
	
	@Test
	public void TestBuscarPorTimeIdPaginavel() {
		Page<Jogador> c = this.jS.BuscarPorTimeIdPaginavel(id, new PageRequest(0,10));
		assertNotNull(c);
	}
}
