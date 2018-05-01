package com.ifg.sistema.sisgesport.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

import com.ifg.sistema.sisgesport.api.entities.Bairro;
import com.ifg.sistema.sisgesport.api.repositorios.BairroRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class BairroServiceTeste {

	@MockBean
	private BairroRepositorio bR;
	
	@Autowired
	private BairroService bS;
	
	private final static String cepBairro = "320";
	private final static String nome = "Governador Henrique Santillo";
	
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.bR.save(Mockito.any(Bairro.class))).willReturn(new Bairro());
		BDDMockito.given(this.bR.findByCepbairro(Mockito.anyString())).willReturn(new Bairro());
		BDDMockito.given(this.bR.findByNome(Mockito.anyString())).willReturn(new Bairro());
	}
	
	@Test
	public void TestPersistenciaBairro() {
		Bairro bairro = this.bS.Salvar(new Bairro());
		assertNotNull(bairro);
	}
	
	@Test
	public void TestBuscarPorCepBairro() {
		Optional<Bairro> bairro = this.bS.BuscarPorCepBairro(cepBairro);
		
		assertTrue(bairro.isPresent());
	}
	
	@Test
	public void TestBuscarPorNome() {
		Optional<Bairro> bairro = this.bS.BuscarPorNome(nome);
		
		assertTrue(bairro.isPresent());
	}
}
