package com.ifg.sistema.sisgesport.api.repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ifg.sistema.sisgesport.api.entities.Estado;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class EstadoRepositorioTeste {
	
	@Autowired
	private EstadoRepositorio esR;
	
	@Before
	public void setUp() throws Exception{
			Estado estado = new Estado();
			estado.setNome("Minas Gerais");
			estado.setUf("MG");
			this.esR.save(estado);
	}
	
	@Test
	public void testBuscarPorNome() {
		Estado estado = this.esR.findByNomeOrUf("Minas Gerais", "");
		assertNotNull(estado);
	}
	
	@Test
	public void testBuscarPorUF() {
		Estado estado = this.esR.findByNomeOrUf("", "MG");
		assertEquals("MG", estado.getUf());
	}
	
	@After
	public final void tearDown() {
		this.esR.deleteAll();
	}
}
