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
	private EnderecoRepositorio eR;

	private static final Estado estado = Estado();
	private static final Municipio municipio = Municipio();
	private static final String cep = "72811320";
	private Long id;

	@Before
	public void setUp() throws Exception {
		
		esR.save(estado);
		mR.save(municipio);
		
		Endereco end = new Endereco();
		end.setMunicipio(municipio);
		end.setComplemento("Qd. 27 Lt. 34");
		end.setCep(cep);
		end.setLogradouro("Avenida gonvernador henrique santillo");
		end.setBairro("Jofre Parada");
		eR.save(end);
		id = end.getId();
		
		Endereco end2 = new Endereco();
		end2.setMunicipio(municipio);
		end2.setCep("72812340");
		end2.setComplemento("Qd. 27 Lt. 30");
		end2.setLogradouro("Rua das Esmeraldas");
		end2.setBairro("Norte Maravilha");
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
	public void testBuscarPorCep() {
		Endereco endereco = eR.findByCep(cep);

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
		mun.setEstado(estado);
		return mun;
	}
}
