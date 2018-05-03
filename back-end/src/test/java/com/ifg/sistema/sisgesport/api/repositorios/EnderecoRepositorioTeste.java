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

import com.ifg.sistema.sisgesport.api.entities.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class EnderecoRepositorioTeste {
	
	@Autowired
	private EstadoRepositorio esR;
	@Autowired
	private MunicipioRepositorio mR;
	@Autowired
	private BairroRepositorio bR;
	@Autowired
	private LogradouroRepositorio lR;
	@Autowired
	private EnderecoRepositorio eR;
	
	private static final String cepMun = "72";
	private static final String cepBairro = "811";
	private static final String cepLogradouro = "320";
	
	private static final Estado estado = Estado();
	private static final Municipio municipio = Municipio();
	private static final Bairro bairro = Bairro();
	private static final Logradouro logradouro = Logradouro();
	private Long id;
	@Before
	public void setUp() throws Exception{
		Endereco end = new Endereco();
		esR.save(estado);
		mR.save(municipio);
		bR.save(bairro);
		lR.save(logradouro);
		end.setComplemento("Qd. 27 Lt. 34");
		end.setLogradouro(logradouro);
		eR.save(end);
		id = end.getId();
		Endereco end2 = new Endereco();
		end2.setComplemento("Qd. 27 Lt. 30");
		end2.setLogradouro(logradouro);
		eR.save(end2);
	}
	
	@After
	public final void tearDown() {
		this.eR.deleteAll();
	}
	
	@Test
	public void testBuscarPorId() {
	Endereco endereco = eR.findOne(id); 	
	
	assertNotNull(endereco);
	}
	
	@Test
	public void testBuscarPorNome() {
		Endereco end = new Endereco();
		esR.save(estado);
		mR.save(municipio);
		bR.save(bairro);
		lR.save(logradouro);
		end.setComplemento("Qd. 27 Lt. 340");
		end.setLogradouro(logradouro);
		eR.save(end);
		
	Endereco endereco = eR.findOne(end.getId()); 	
	
	assertNotNull(endereco);
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
	
	private static Logradouro Logradouro() {
		Logradouro log = new Logradouro();
		log.setLogradouro("Avenida Ouro Preto");
		log.setCep_logradouro(cepLogradouro);
		log.setBairro(bairro);
		return log;
	}
}
