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

import com.ifg.sistema.sisgesport.api.entities.Time;
import com.ifg.sistema.sisgesport.api.repositorios.TimeRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class TimeServiceTeste {
	@MockBean
	private TimeRepositorio tR;
	@Autowired
	private TimeService tS;
	private static final Long id = (long)1;
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.tR.save(Mockito.any(Time.class))).willReturn(new Time());
		BDDMockito.given(this.tR.findOne(Mockito.anyLong())).willReturn(new Time());
		BDDMockito.given(this.tR.findByEquipeCodigoEquipe(Mockito.anyString())).willReturn(new Time());
		BDDMockito.given(this.tR.findByEquipeId(Mockito.anyLong())).willReturn(new ArrayList<Time>());
		BDDMockito.given(this.tR.findByEquipeId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
		.willReturn(new PageImpl<Time>(new ArrayList<Time>()));
	}
	
	@Test
	public void TestPersistenciaEvento() {
		Time e = this.tS.Salvar(new Time());
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscaPorId() {
		Optional<Time> e = this.tS.BuscarPorId((long)1);
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscarPorEquipeCodigoEquipe() {
		Optional<Time> e = this.tS.BuscarPorEquipeCodigoEquipe("teste");
		assertNotNull(e);
	}
		
	@Test
	public void TestBuscarPorEquipeId() {
		Optional<List<Time>> c = this.tS.BuscarPorEquipeId(id);
		assertTrue(c.isPresent());
	}
	
	@Test
	public void TestBuscarPorEquipeIdPaginavel() {
		Page<Time> c = this.tS.BuscarPorEquipeIdPaginavel(id, new PageRequest(0,10));
		assertNotNull(c);
	}
}
