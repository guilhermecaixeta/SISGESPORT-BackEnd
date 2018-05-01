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

import com.ifg.sistema.sisgesport.api.entities.Estado;
import com.ifg.sistema.sisgesport.api.repositorios.EstadoRepositorio;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class EstadoServiceTeste {

	@MockBean
	private EstadoRepositorio eR;
	@Autowired
	private EstadoService eS;
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.eR.save(Mockito.any(Estado.class))).willReturn(new Estado());
		BDDMockito.given(this.eR.findOne(Mockito.anyLong())).willReturn(new Estado());
		BDDMockito.given(this.eR.findByNomeOrUf(Mockito.anyString(), Mockito.anyString())).willReturn(new Estado());
	}
	
	@Test
	public void TestPersistenciaEstado() {
		Estado e = this.eS.Salvar(new Estado());
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscaPorId() {
		Optional<Estado> e = this.eS.BuscarPorId((long)1);
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscaPorNomeouUF() {
		Optional<Estado> e = this.eS.BuscarPorNomeOuUF("TesteNome", "TesteUF");
		assertNotNull(e);
	}
}
