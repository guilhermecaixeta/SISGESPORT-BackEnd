package com.ifg.sistema.sisgesport.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ifg.sistema.sisgesport.api.entities.Cargo;
import com.ifg.sistema.sisgesport.api.repositorios.CargoRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class CargoServiceTeste {

	@MockBean
	private CargoRepositorio cR;
	
	@Autowired
	private CargoService cS;
	
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.cR.save(Mockito.any(Cargo.class))).willReturn(new Cargo());
		BDDMockito.given(this.cR.findByInstituicaoId(Mockito.anyLong())).willReturn(new ArrayList<Cargo>());
		BDDMockito.given(this.cR.findByInstituicaoId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
		.willReturn(new PageImpl<Cargo>(new ArrayList<Cargo>()));
		BDDMockito.given(this.cR.findByNomeContains(Mockito.anyString())).willReturn(new ArrayList<Cargo>());
	}
	
	@Test
	public void TestBuscarPorInstituicaoId() {
		Optional<List<Cargo>> cargo = this.cS.BuscarPorInstituicaoId((long) 1);
		
		assertTrue(cargo.isPresent());
	}
	
	@Test
	public void TestBuscarPorInstituicaoIdPaginavel() {
		Page<Cargo> cargo = this.cS.BuscarPorInstituicaoIdPaginavel((long)1, new PageRequest(0,10));
		
		assertNotNull(cargo);
	}
	
	@Test
	public void TestBuscarPorNome() {
		Optional<List<Cargo>> cargo = this.cS.BuscarPorNome("teste");
		
		assertTrue(cargo.isPresent());
	}
	
	@Test
	public void TestPersistenciaCargo() {
		Cargo cargo = this.cS.Salvar(new Cargo());
		
		assertNotNull(cargo);
	}
}
