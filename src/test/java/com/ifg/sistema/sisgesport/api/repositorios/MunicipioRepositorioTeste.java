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
import com.ifg.sistema.sisgesport.api.entities.Municipio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class MunicipioRepositorioTeste {
	@Autowired
	private EstadoRepositorio esR;
	@Autowired
	private MunicipioRepositorio mR;


	private static final String cepMun = "72";
	
	private static final Estado estado = Estado();
	
	@Before
	public void setUp() throws Exception{
		esR.save(estado);
		Municipio municipio = new Municipio();
		municipio.setCep_municipio(cepMun);
		municipio.setEstado(estado);
		municipio.setNome("Luziânia");
		municipio.setSigla("LZA");
		mR.save(municipio);

	}
	
	@After
	public final void tearDown() {
		this.mR.deleteAll();
	}
	
	@Test
	public void testBuscarCep() {
		Municipio mun = this.mR.findByCepmunicipio(cepMun);		
		assertEquals(cepMun, mun.getCep_municipio());
	}

	@Test
	public void testBuscarPorNome() {
		Municipio mun = this.mR.findByNomeOrSigla("Luziânia", "");
		assertNotNull(mun);
	}
	
	private static Estado Estado() {
		Estado est = new Estado();
		est.setNome("Goiás");
		est.setUf("GO");
		return est;
	}
}
