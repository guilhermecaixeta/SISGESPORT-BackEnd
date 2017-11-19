package com.ifg.sistema.sisgesport.api.repositorios;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ifg.sistema.sisgesport.api.entities.Penalidade;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class PenalidadeRepositorioTeste {
	
	@Autowired
	private PenalidadeRepositorio pR;
	private Penalidade penal;
	
	@Before
	public void setUp() throws Exception{
		Penalidade p = new Penalidade();
		p.setDescricao("Falta grave");
		p.setNome("Cartão Vermelho");
		pR.save(p);
		penal = p;
	}
	
	@After
	public final void tearDown() {
		pR.deleteAll();
	}
	
	@Test
	public void TesteBuscarPorId(){
		Penalidade p = pR.findById(penal.getId());
		
		assertNotNull(p);
	}
}
