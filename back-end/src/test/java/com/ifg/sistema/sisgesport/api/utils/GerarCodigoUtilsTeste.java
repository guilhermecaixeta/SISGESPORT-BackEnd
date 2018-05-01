package com.ifg.sistema.sisgesport.api.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.ifg.sistema.sisgesport.api.utils.GeradorCodigoUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class GerarCodigoUtilsTeste {
	
	@Test
	public void testeGeracaoCodigoEquipe() throws Exception{
		String codigo = GeradorCodigoUtils.GerarCodigoUnicoEquipe();
		
		assertNotNull(codigo);
	}
	
	@Test
	public void testeGeracaoCodigoEvento() throws Exception{
		String codigo = GeradorCodigoUtils.GerarCodigoUnicoEvento();
		
		assertNotNull(codigo);
	}
	
	@Test
	public void testeGeracaoCodigoEquipeTamanho() throws Exception{
		String codigo = GeradorCodigoUtils.GerarCodigoUnicoEquipe();
		
		assertEquals(20, codigo.length());
	}
	
	@Test
	public void testeGeracaoCodigoEventoTamanho() throws Exception{
		String codigo = GeradorCodigoUtils.GerarCodigoUnicoEvento();
		
		assertEquals(15, codigo.length());
	}
}
