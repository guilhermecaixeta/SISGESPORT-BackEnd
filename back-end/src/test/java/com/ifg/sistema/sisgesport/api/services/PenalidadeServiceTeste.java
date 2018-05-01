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

import com.ifg.sistema.sisgesport.api.entities.Penalidade;
import com.ifg.sistema.sisgesport.api.repositorios.PenalidadeRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class PenalidadeServiceTeste {
	@MockBean
	private PenalidadeRepositorio pR;
	@Autowired
	private PenalidadeService pS;
	private static final Long id = (long)1;
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.pR.save(Mockito.any(Penalidade.class))).willReturn(new Penalidade());
		BDDMockito.given(this.pR.findOne(Mockito.anyLong())).willReturn(new Penalidade());
		BDDMockito.given(this.pR.findByNome(Mockito.anyString())).willReturn(new Penalidade());
		BDDMockito.given(this.pR.findByModalidadeId(Mockito.anyLong())).willReturn(new ArrayList<Penalidade>());
	}
	
	@Test
	public void TestPersistenciaEvento() {
		Penalidade e = this.pS.Salvar(new Penalidade());
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscarPorNome() {
		Optional<Penalidade> e = this.pS.BuscarPorNome("teste");
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscaPorId() {
		Optional<Penalidade> e = this.pS.BuscarPorId((long)1);
		assertNotNull(e);
	}
		
	@Test
	public void TestBuscarPorModalidadeId() {
		Optional<List<Penalidade>> c = this.pS.BuscarPorModalidadeId(id);
		assertTrue(c.isPresent());
	}
	
}
