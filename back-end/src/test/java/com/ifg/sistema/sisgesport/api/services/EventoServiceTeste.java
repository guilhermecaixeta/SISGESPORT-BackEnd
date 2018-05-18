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

import com.ifg.sistema.sisgesport.api.entities.Evento;
import com.ifg.sistema.sisgesport.api.repositorios.EventoRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class EventoServiceTeste {
	@MockBean
	private EventoRepositorio eR;
	@Autowired
	private EventoService eS;
	private static final String matriculaSiap = "teste";
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.eR.save(Mockito.any(Evento.class))).willReturn(new Evento());
		BDDMockito.given(this.eR.findOne(Mockito.anyLong())).willReturn(new Evento());
		BDDMockito.given(this.eR.findByCriadorMatricula(Mockito.anyString())).willReturn(new ArrayList<Evento>());
		BDDMockito.given(this.eR.findByCriadorMatricula(Mockito.anyString(), Mockito.any(PageRequest.class)))
		.willReturn(new PageImpl<Evento>(new ArrayList<Evento>()));
	}
	
	@Test
	public void TestPersistenciaEvento() {
		Evento e = this.eS.Salvar(new Evento());
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscaPorId() {
		Optional<Evento> e = this.eS.BuscarPorId((long)1);
		assertNotNull(e);
	}
		
	@Test
	public void TestBuscaPorMatriculaCriador() {
		Optional<List<Evento>> c = this.eS.BuscarPorMatriculaCriador(matriculaSiap);
		assertTrue(c.isPresent());
	}
	
	@Test
	public void TestBuscaPorMatriculaCriadorPaginavel() {
		Page<Evento> c = this.eS.BuscarPorMatriculaCriador(matriculaSiap, new PageRequest(0,10));
		assertNotNull(c);
	}
}
