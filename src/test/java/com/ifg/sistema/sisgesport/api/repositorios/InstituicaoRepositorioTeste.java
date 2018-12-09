package com.ifg.sistema.sisgesport.api.repositorios;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ifg.sistema.sisgesport.api.entities.Cargo;
import com.ifg.sistema.sisgesport.api.entities.Instituicao;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class InstituicaoRepositorioTeste {

	@Autowired
	private InstituicaoRepositorio instituicaoRepo;
	@Autowired
	private CargoRepositorio cargoRepositorio;
	
	private static final Cargo cargo = CarregarCargo();
	
	@Before
	public void setUp() throws Exception{
		cargoRepositorio.save(cargo);
		List<Cargo> cargos = new ArrayList<Cargo>();
		cargos.add(cargo);
		Instituicao instituto = new Instituicao();
		instituto.setDescricao("IFG - Instituto Federal de Goiás - Campus Luziânia");
		instituto.setNome("IFG - Campus Luziânia.");
		instituto.setCargos(cargos);
		instituicaoRepo.save(instituto);
	}
	
//	@After
//	public final void tearDown() {
//		instituicaoRepo.deleteAll();
//	}
	
	@Test
	public void TesteBuscarInstituto() {
		Instituicao ins = instituicaoRepo.findByNome("IFG - Campus Luziânia.");
		
		assertNotNull(ins);
	}
	
	private static Cargo CarregarCargo() {
		Cargo c = new Cargo();
		c.setDescricao("Lecionar aulas");
		c.setNome("Professor Mestrado");
		return c;
	}
}
