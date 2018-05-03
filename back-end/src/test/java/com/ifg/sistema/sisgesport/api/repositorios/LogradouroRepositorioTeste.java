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
	
	import com.ifg.sistema.sisgesport.api.entities.*;
	
	@RunWith(SpringRunner.class)
	@SpringBootTest
	@ActiveProfiles("teste")
	public class LogradouroRepositorioTeste {
		
	@Autowired
	private EstadoRepositorio esR;
	@Autowired
	private MunicipioRepositorio mR;
	@Autowired
	private BairroRepositorio bR;
	@Autowired
	private LogradouroRepositorio lR;
	@Autowired

	private static final String cepMun = "72";
	private static final String cepBairro = "811";
	private static final String cepLogradouro = "320";
	
	private static final Estado estado = Estado();
	private static final Municipio municipio = Municipio();
	private static final Bairro bairro = Bairro();
	
	@Before
	public void setUp() throws Exception{
		esR.save(estado);
		mR.save(municipio);
		bR.save(bairro);
		Logradouro log = new Logradouro();
		log.setLogradouro("Avenida Ouro Preto");
		log.setCep_logradouro(cepLogradouro);
		log.setBairro(bairro);
		lR.save(log);
	}
	
	@After
	public final void tearDown() {
		this.lR.deleteAll();
	}
	
	@Test
	public void testBuscarPorCepNotNull() {
		Logradouro log= lR.findByCepLogradouro(cepLogradouro); 	
		
		assertNotNull(log);
	}
	
	@Test
	public void testBuscarPorCepEquals() {
		Logradouro log= lR.findByCepLogradouro(cepLogradouro);
		
		assertEquals(cepLogradouro, log.getCep_logradouro());
	}
	
	@Test
	public void testBuscarPorCepCompleto() {
		Logradouro log= lR.findByBairroMunicipioCepMunicipioOrBairroCepBairroOrCepLogradouro(cepMun, cepBairro, cepLogradouro);
		
		assertNotNull(log);
	}
	
	private static Estado Estado() {
		Estado est = new Estado();
		est.setNome("Goiás");
		est.setUf("GO");
		return est;
	}
	
	private static Municipio Municipio() {
		Municipio mun = new Municipio();
		mun.setNome("Luziania");
		mun.setCep_municipio(cepMun);
		mun.setSigla("LZA");
		mun.setEstado(estado);
		return mun;
	}
	
	private static Bairro Bairro() {
		Bairro bai = new Bairro();
		bai.setNome("Bairro Jofre Mozart Parada");
		bai.setCepBairro(cepBairro);
		bai.setMunicipio(municipio);
		return bai;
		}
	}
