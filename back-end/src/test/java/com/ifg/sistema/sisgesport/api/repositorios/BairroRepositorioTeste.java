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

import com.ifg.sistema.sisgesport.api.entities.Bairro;
import com.ifg.sistema.sisgesport.api.entities.Estado;
import com.ifg.sistema.sisgesport.api.entities.Municipio;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class BairroRepositorioTeste {

	@Autowired
	private EstadoRepositorio esR;
	@Autowired
	private MunicipioRepositorio mR;
	@Autowired
	private BairroRepositorio bR;

	private static final String cepMun = "72";
	private static final String cepBairro = "811";

	
	private static final Estado estado = Estado();
	private static final Municipio municipio = Municipio();
	
	@Before
	public void setUp() throws Exception{
		Bairro bairro = new Bairro();
		esR.save(estado);
		mR.save(municipio);
		bairro.setCepBairro(cepBairro);
		bairro.setMunicipio(municipio);
		bairro.setNome("Bairro Jofre Parada II");
		bR.save(bairro);
	}
	
	@After
	public final void tearDown() {
		this.bR.deleteAll();
	}
	
	@Test
	public void testBuscarCep() {
		Bairro bairro = this.bR.findByCepBairro(cepBairro);		
		assertEquals(bairro.getCepBairro(), cepBairro);
	}

	@Test
	public void testBuscarPorNome() {
		Bairro bairro = this.bR.findByNome("Bairro Jofre Parada");
		assertNotNull(bairro.getNome());
	}
	
	private static Estado Estado() {
		Estado est = new Estado();
		est.setNome("Distrito Federal");
		est.setUf("DF");
		return est;
	}
	
	private static Municipio Municipio() {
		Municipio mun = new Municipio();
		mun.setNome("Brasília");
		mun.setCepMunicipio(cepMun);
		mun.setSigla("BRA");
		mun.setEstado(estado);
		return mun;
	}
}
