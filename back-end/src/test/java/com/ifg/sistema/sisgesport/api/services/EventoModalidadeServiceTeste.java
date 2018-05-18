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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ifg.sistema.sisgesport.api.entities.EventoModalidade;
import com.ifg.sistema.sisgesport.api.repositorios.EventoModalidadeRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class EventoModalidadeServiceTeste {

	@MockBean
	private EventoModalidadeRepositorio evR;
	@Autowired
	private EventoModalidadeService evS;
	private static final Long id = (long)1;
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.evR.save(Mockito.any(EventoModalidade.class))).willReturn(new EventoModalidade());
		BDDMockito.given(this.evR.findOne(Mockito.anyLong())).willReturn(new EventoModalidade());
		BDDMockito.given(this.evR.findByEventoId(Mockito.anyLong())).willReturn(new ArrayList<EventoModalidade>());
		BDDMockito.given(this.evR.findByModalidadeId(Mockito.anyLong())).willReturn(new ArrayList<EventoModalidade>());
	}
	
	@Test
	public void TestPersistenciaEvento() {
		EventoModalidade e = this.evS.Salvar(new EventoModalidade());
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscaPorId() {
		Optional<EventoModalidade> e = this.evS.BuscarPorId((long)1);
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscaPorIdEvento() {
		Optional<List<EventoModalidade>> c = this.evS.BuscarPorEventoId(id);
		assertTrue(c.isPresent());
	}
	
	@Test
	public void TestBuscaPorIdModalidade() {
		Optional<List<EventoModalidade>> c = this.evS.BuscarPorModalidadeId(id);
		assertTrue(c.isPresent());
	}
}
