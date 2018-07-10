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

import com.ifg.sistema.sisgesport.api.entities.Partida;
import com.ifg.sistema.sisgesport.api.repositorios.PartidaRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class PartidaServiceTeste {
	@MockBean
	private PartidaRepositorio pR;
	@Autowired
	private PartidaService pS;
	private static final Long id = (long)1;
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.pR.save(Mockito.any(Partida.class))).willReturn(new Partida());
		BDDMockito.given(this.pR.findOne(Mockito.anyLong())).willReturn(new Partida());
		BDDMockito.given(this.pR.findByTimeCasaId(Mockito.anyLong())).willReturn(new ArrayList<Partida>());
		BDDMockito.given(this.pR.findByTimeCasaId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
		.willReturn(new PageImpl<Partida>(new ArrayList<Partida>()));
		BDDMockito.given(this.pR.findByEventoId(Mockito.anyLong())).willReturn(new ArrayList<Partida>());
		BDDMockito.given(this.pR.findByEventoId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
		.willReturn(new PageImpl<Partida>(new ArrayList<Partida>()));
	}
	
	@Test
	public void TestPersistenciaEvento() {
		Partida e = this.pS.Salvar(new Partida());
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscaPorId() {
		Optional<Partida> e = this.pS.BuscarPorId((long)1);
		assertNotNull(e);
	}
		
	@Test
	public void TestBuscarPorTimeCasaId() {
		Optional<List<Partida>> c = this.pS.BuscarPorTimeCasaId(id);
		assertTrue(c.isPresent());
	}
	
	@Test
	public void TestBuscarPorTimeCasaIdPaginavel() {
		Page<Partida> c = this.pS.BuscarPorTimeCasaIdPaginavel(id, new PageRequest(0,10));
		assertNotNull(c);
	}
	
	@Test
	public void TestBuscarPorEventoId() {
		Optional<List<Partida>> c = this.pS.BuscarPorEventoId(id);
		assertTrue(c.isPresent());
	}
	
	@Test
	public void TestBuscarPorEventoIdPaginavel() {
		Page<Partida> c = this.pS.BuscarPorEventoIdPaginavel(id, new PageRequest(0,10));
		assertNotNull(c);
	}
}
