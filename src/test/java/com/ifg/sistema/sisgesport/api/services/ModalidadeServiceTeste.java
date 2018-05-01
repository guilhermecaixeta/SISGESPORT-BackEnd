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

import com.ifg.sistema.sisgesport.api.entities.Modalidade;
import com.ifg.sistema.sisgesport.api.repositorios.ModalidadeRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class ModalidadeServiceTeste {
	@MockBean
	private ModalidadeRepositorio mR;
	@Autowired
	private ModalidadeService mS;
	private static final String matriculaSiap = "teste";
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.mR.save(Mockito.any(Modalidade.class))).willReturn(new Modalidade());
		BDDMockito.given(this.mR.findOne(Mockito.anyLong())).willReturn(new Modalidade());
		BDDMockito.given(this.mR.findByNome(Mockito.anyString())).willReturn(new Modalidade());

	}
	
	@Test
	public void TestPersistenciaEvento() {
		Modalidade e = this.mS.Salvar(new Modalidade());
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscaPorId() {
		Optional<Modalidade> e = this.mS.BuscarPorId((long)1);
		assertNotNull(e);
	}
		
	@Test
	public void TestBuscarPorNome() {
		Optional<Modalidade> c = this.mS.BuscarPorNome(matriculaSiap);
		assertTrue(c.isPresent());
	}
}
