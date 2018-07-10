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

import com.ifg.sistema.sisgesport.api.entities.Municipio;
import com.ifg.sistema.sisgesport.api.repositorios.MunicipioRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class MunicipioServiceTeste {
	@MockBean
	private MunicipioRepositorio mR;
	@Autowired
	private MunicipioService mS;
	private static final String cepMunicipio = "123";
	private static final Long id=(long)1;
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.mR.save(Mockito.any(Municipio.class))).willReturn(new Municipio());
		BDDMockito.given(this.mR.findOne(Mockito.anyLong())).willReturn(new Municipio());
		BDDMockito.given(this.mR.findByNome(Mockito.anyString())).willReturn(new Municipio());
		BDDMockito.given(this.mR.findByEstadoId(Mockito.anyLong())).willReturn(new ArrayList<Municipio>());
		BDDMockito.given(this.mR.findByEstadoId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
		.willReturn(new PageImpl<Municipio>(new ArrayList<Municipio>()));
	}
	
	@Test
	public void TestPersistenciaEvento() {
		Municipio e = this.mS.Salvar(new Municipio());
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscaPorId() {
		Optional<Municipio> e = this.mS.BuscarPorId((long)1);
		assertNotNull(e);
	}
		
	@Test
	public void TestBuscarPorEstadoId() {
		Optional<List<Municipio>> c = this.mS.BuscarPorEstadoId(id);
		assertTrue(c.isPresent());
	}
	
	@Test
	public void TestBuscarPorEstadoIdPaginavel() {
		Page<Municipio> c = this.mS.BuscarPorEstadoIdPaginavel(id, new PageRequest(0,10));
		assertNotNull(c);
	}
}
