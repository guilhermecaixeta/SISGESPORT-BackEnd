package com.ifg.sistema.sisgesport.api.repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

	private static final Estado estado = carregarEstado();
	private static final Municipio municipio = carregarMunicipio();

	@Before
	public void setUp() throws Exception {
		esR.save(estado);
		municipio.setEstado(estado);
		mR.save(municipio);

	}

//	@After
//	public final void tearDown() {
//		this.mR.deleteAll();
//	}

	@Test
	public void testBuscarPorNome() {
		Municipio mun = this.mR.findByNome("Rio de Janeiro - Capital");
		assertNotNull(mun);
	}

	@Test
	public void testBuscarEstado() {
		List<Municipio> mun = this.mR.findByEstadoId(estado.getId());
		assertEquals(1, mun.size());
	}

	@Test
	public void testBuscarEstadoPaginavel() {
		PageRequest page = new PageRequest(0, 10);
		Page<Municipio> mun = this.mR.findByEstadoId(estado.getId(), page);
		assertEquals(1, mun.getNumberOfElements());
	}

	private static Estado carregarEstado() {
		Estado est = new Estado();
		est.setNome("Rio de Janeiro");
		est.setUf("RJ");
		return est;
	}

	private static Municipio carregarMunicipio() {
		Municipio municipio = new Municipio();
		municipio.setNome("Rio de Janeiro - Capital");
		return municipio;
	}
}
