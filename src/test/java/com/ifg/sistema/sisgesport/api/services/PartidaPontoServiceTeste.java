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

import com.ifg.sistema.sisgesport.api.entities.Partida_Ponto;
import com.ifg.sistema.sisgesport.api.repositorios.PartidaPontoRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class PartidaPontoServiceTeste {
	@MockBean
	private PartidaPontoRepositorio ppR;
	@Autowired
	private PartidaPontoService ppS;
	private static final Long id = (long)1;
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.ppR.save(Mockito.any(Partida_Ponto.class))).willReturn(new Partida_Ponto());
		BDDMockito.given(this.ppR.findOne(Mockito.anyLong())).willReturn(new Partida_Ponto());
		BDDMockito.given(this.ppR.findByJogadorId(Mockito.anyLong())).willReturn(new ArrayList<Partida_Ponto>());
		BDDMockito.given(this.ppR.findByPartidaId(Mockito.anyLong())).willReturn(new ArrayList<Partida_Ponto>());
		BDDMockito.given(this.ppR.findByTipoPontoId(Mockito.anyLong())).willReturn(new ArrayList<Partida_Ponto>());
	}
	
	@Test
	public void TestPersistenciaEvento() {
		Partida_Ponto e = this.ppS.Salvar(new Partida_Ponto());
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscaPorId() {
		Optional<Partida_Ponto> e = this.ppS.BuscarPorId((long)1);
		assertNotNull(e);
	}
		
	@Test
	public void TestBuscarPorPartidaId() {
		Optional<List<Partida_Ponto>> c = this.ppS.BuscarPorPartidaId(id);
		assertTrue(c.isPresent());
	}
	
	@Test
	public void TestBuscarPorTipoPontoId() {
		Optional<List<Partida_Ponto>> c = this.ppS.BuscarPorTipoPontoId(id);
		assertTrue(c.isPresent());
	}
	
	@Test
	public void TestBuscarPorJogadorId() {
		Optional<List<Partida_Ponto>> c = this.ppS.BuscarPorJogadorId(id);
		assertTrue(c.isPresent());
	}
	
}
