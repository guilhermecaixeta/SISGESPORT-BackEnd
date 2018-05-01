package com.ifg.sistema.sisgesport.api.services;

import static org.junit.Assert.assertNotNull;
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

import com.ifg.sistema.sisgesport.api.entities.Logradouro;
import com.ifg.sistema.sisgesport.api.repositorios.LogradouroRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class LogradouroServiceTeste {
	@MockBean
	private LogradouroRepositorio lR;
	@Autowired
	private LogradouroService lS;
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.lR.save(Mockito.any(Logradouro.class))).willReturn(new Logradouro());
		BDDMockito.given(this.lR.findOne(Mockito.anyLong())).willReturn(new Logradouro());
		BDDMockito.given(this.lR.findByCepLogradouro(Mockito.anyString())).willReturn(new Logradouro());
		BDDMockito.given(
				this.lR.findByBairroMunicipioCepMunicipioOrBairroCepbairroOrCepLogradouro(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
		.willReturn(new Logradouro());
	}
	
	@Test
	public void TestPersistenciaLogradouro() {
		Logradouro e = this.lS.Salvar(new Logradouro());
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscaPorId() {
		Optional<Logradouro> e = this.lS.BuscarPorId((long)1);
		assertNotNull(e);
	}
		
	@Test
	public void BuscarPorCepLogradouro() {
		Optional<Logradouro> e = this.lS.BuscarPorCepLogradouro("123");
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscarPorCepCompletod() {
		Optional<Logradouro> e = this.lS.BuscarPorCepCompleto("123", "123", "123");
		assertNotNull(e);
	}
}
