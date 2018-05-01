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

import com.ifg.sistema.sisgesport.api.entities.TipoPonto;
import com.ifg.sistema.sisgesport.api.repositorios.TipoPontoRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class TipoPontoServiceTeste {
	@MockBean
	private TipoPontoRepositorio tpR;
	@Autowired
	private TipoPontoService tpS;
	private static final Long id = (long)1;
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.tpR.save(Mockito.any(TipoPonto.class))).willReturn(new TipoPonto());
		BDDMockito.given(this.tpR.findOne(Mockito.anyLong())).willReturn(new TipoPonto());
		BDDMockito.given(this.tpR.findByNome(Mockito.anyString())).willReturn(new TipoPonto());
		BDDMockito.given(this.tpR.findByModalidadeId(Mockito.anyLong())).willReturn(new ArrayList<TipoPonto>());
		BDDMockito.given(this.tpR.findByModalidadeId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
		.willReturn(new PageImpl<TipoPonto>(new ArrayList<TipoPonto>()));
	}
	
	@Test
	public void TestPersistenciaEvento() {
		TipoPonto e = this.tpS.Salvar(new TipoPonto());
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscaPorId() {
		Optional<TipoPonto> e = this.tpS.BuscarPorId((long)1);
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscarPorNome() {
		Optional<TipoPonto> e = this.tpS.BuscarPorNome("teste");
		assertNotNull(e);
	}
		
	@Test
	public void TestBuscarPorModalidadeId() {
		Optional<List<TipoPonto>> c = this.tpS.BuscarPorModalidadeId(id);
		assertTrue(c.isPresent());
	}
	
	@Test
	public void TestBuscarPorModalidadeIdPaginavel() {
		Page<TipoPonto> c = this.tpS.BuscarPorModalidadeIdPaginavel(id, new PageRequest(0,10));
		assertNotNull(c);
	}
}
