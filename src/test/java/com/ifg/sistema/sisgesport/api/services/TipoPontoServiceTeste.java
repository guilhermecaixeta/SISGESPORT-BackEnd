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

import com.ifg.sistema.sisgesport.api.entities.Tipo_Ponto;
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
		BDDMockito.given(this.tpR.save(Mockito.any(Tipo_Ponto.class))).willReturn(new Tipo_Ponto());
		BDDMockito.given(this.tpR.findOne(Mockito.anyLong())).willReturn(new Tipo_Ponto());
		BDDMockito.given(this.tpR.findByNome(Mockito.anyString())).willReturn(new Tipo_Ponto());
		BDDMockito.given(this.tpR.findByModalidadeId(Mockito.anyLong())).willReturn(new ArrayList<Tipo_Ponto>());
		BDDMockito.given(this.tpR.findByModalidadeId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
		.willReturn(new PageImpl<Tipo_Ponto>(new ArrayList<Tipo_Ponto>()));
	}
	
	@Test
	public void TestPersistenciaEvento() {
		Tipo_Ponto e = this.tpS.Salvar(new Tipo_Ponto());
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscaPorId() {
		Optional<Tipo_Ponto> e = this.tpS.BuscarPorId((long)1);
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscarPorNome() {
		Optional<Tipo_Ponto> e = this.tpS.BuscarPorNome("teste");
		assertNotNull(e);
	}
		
	@Test
	public void TestBuscarPorModalidadeId() {
		Optional<List<Tipo_Ponto>> c = this.tpS.BuscarPorModalidadeId(id);
		assertTrue(c.isPresent());
	}
	
	@Test
	public void TestBuscarPorModalidadeIdPaginavel() {
		Page<Tipo_Ponto> c = this.tpS.BuscarPorModalidadeIdPaginavel(id, new PageRequest(0,10));
		assertNotNull(c);
	}
}
