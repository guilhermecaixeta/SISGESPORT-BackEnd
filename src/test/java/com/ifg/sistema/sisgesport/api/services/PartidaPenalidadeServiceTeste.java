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

import com.ifg.sistema.sisgesport.api.entities.PartidaPenalidade;
import com.ifg.sistema.sisgesport.api.repositorios.PartidaPenalidadeRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class PartidaPenalidadeServiceTeste {
	@MockBean
	private PartidaPenalidadeRepositorio ppR;
	@Autowired
	private PartidaPenalidadeService ppS;
	private static final Long id = (long)1;
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.ppR.save(Mockito.any(PartidaPenalidade.class))).willReturn(new PartidaPenalidade());
		BDDMockito.given(this.ppR.findOne(Mockito.anyLong())).willReturn(new PartidaPenalidade());
		BDDMockito.given(this.ppR.findByJogadorId(Mockito.anyLong())).willReturn(new ArrayList<PartidaPenalidade>());
		BDDMockito.given(this.ppR.findByPartidaId(Mockito.anyLong())).willReturn(new ArrayList<PartidaPenalidade>());
		BDDMockito.given(this.ppR.findByPenalidadeId(Mockito.anyLong())).willReturn(new ArrayList<PartidaPenalidade>());
	}
	
	@Test
	public void TestPersistenciaEvento() {
		PartidaPenalidade e = this.ppS.Salvar(new PartidaPenalidade());
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscaPorId() {
		Optional<PartidaPenalidade> e = this.ppS.BuscarPorId((long)1);
		assertNotNull(e);
	}
		
	@Test
	public void TestBuscarPorJogadorId() {
		Optional<List<PartidaPenalidade>> c = this.ppS.BuscarPorJogadorId(id);
		assertTrue(c.isPresent());
	}
	
	@Test
	public void TestBuscarPorPartidaId() {
		Optional<List<PartidaPenalidade>> c = this.ppS.BuscarPorPartidaId(id);
		assertTrue(c.isPresent());
	}
	
	@Test
	public void TestBuscarPorPenalidadeId() {
		Optional<List<PartidaPenalidade>> c = this.ppS.BuscarPorPenalidadeId(id);
		assertTrue(c.isPresent());
	}
}
