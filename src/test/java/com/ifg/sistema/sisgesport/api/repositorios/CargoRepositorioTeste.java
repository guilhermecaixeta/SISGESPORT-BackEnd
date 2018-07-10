package com.ifg.sistema.sisgesport.api.repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
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

import com.ifg.sistema.sisgesport.api.entities.Cargo;
import com.ifg.sistema.sisgesport.api.entities.Instituicao;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class CargoRepositorioTeste {
	
	@Autowired
	private InstituicaoRepositorio instituicaoRepo;
	@Autowired
	private CargoRepositorio cargoRepositorio;
	
	private static final Instituicao instituto = CarregarInstituto();
	private static final Cargo cargo = CarregarCargo();
	@Before
	public void setUp() throws Exception{		
		instituicaoRepo.save(instituto);	
		
		List<Instituicao> lista = new ArrayList<Instituicao>();
		lista.add(instituto);
		cargo.setInstituicao(lista);
		cargoRepositorio.save(cargo);
	}

	@Test
	public void TesteBuscarPorNome() {
		List<Cargo> cg = cargoRepositorio.findByNomeContains("Professor");
		
		assertNotNull(cg);
	}
	
	@Test
	public void TesteBuscarPorIdInstituto() {
		List<Cargo> listaCargo = cargoRepositorio.findByInstituicaoId(instituto.getId());
		
		assertEquals(1, listaCargo.size());
	}
	
	@Test
	public void TesteBuscarPorNomeInstitutoPaginavel() {
		PageRequest page = new PageRequest(0, 5);
		Page<Cargo> listaCargo = cargoRepositorio.findByInstituicaoId(instituto.getId(), page);
		
		assertEquals(1, listaCargo.getTotalElements());
	}
	
	private static Instituicao CarregarInstituto() {
		Instituicao inst = new Instituicao();
		inst.setDescricao("Instituto de Ciência e Tecnologia do estado de Goiás.");
		inst.setNome("IFG - Instituto Federal de Goiás - Campus Luziânua");
		return inst;
	}
	
	private static Cargo CarregarCargo() {
		Cargo cargo = new Cargo();
		cargo.setDescricao("Administração de eventos.");
		cargo.setNome("Administração de eventos");
		return cargo;
	}
}
