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
	private static final Estado estado = carregarEstado();
	@Before
	public void setUp() throws Exception{
			this.esR.save(estado);
	}
	
	@Test
	public void testBuscarPorNome() {
		Estado estado = this.esR.findByNomeOrUf("Rio Grande do Sul", "");
		assertNotNull(estado);
	}
	
	@Test
	public void testBuscarPorUF() {
		Estado estado = this.esR.findByNomeOrUf("", "RS");
		assertEquals("RS", estado.getUf());
	}
	
//	@After
//	public final void tearDown() {
//		this.esR.deleteAll();
//	}
	
	public static Estado carregarEstado() {
		Estado estado = new Estado();
		estado.setNome("Rio Grande do Sul");
		estado.setUf("RS");
		return estado;
	}
}
