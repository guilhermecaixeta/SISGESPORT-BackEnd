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

import com.ifg.sistema.sisgesport.api.entities.Equipe;
import com.ifg.sistema.sisgesport.api.repositorios.EquipeRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class EquipeServiceTeste {

	@MockBean
	private EquipeRepositorio eR;
	@Autowired
	private EquipeService eS;
	private static final Long id = (long)1;
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.eR.save(Mockito.any(Equipe.class))).willReturn(new Equipe());
		BDDMockito.given(this.eR.findOne(Mockito.anyLong())).willReturn(new Equipe());
		BDDMockito.given(this.eR.findByCodigoEquipe(Mockito.anyString())).willReturn(new Equipe());
		BDDMockito.given(this.eR.findByEventoId(Mockito.anyLong())).willReturn(new ArrayList<Equipe>());
		BDDMockito.given(this.eR.findByEventoId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
		.willReturn(new PageImpl<Equipe>(new ArrayList<Equipe>()));
	}
	
	@Test
	public void TestPersistenciaEndereco() {
		Equipe e = this.eS.Salvar(new Equipe());
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscaPorId() {
		Optional<Equipe> e = this.eS.BuscarPorId((long)1);
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscaPorCodigoEvento() {
		Optional<Equipe> e = this.eS.BuscarPorCodigoEquipe("teste");
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscaPorIdEvento() {
		Optional<List<Equipe>> c = this.eS.BuscarEquipePorIdEvento(id);
		assertTrue(c.isPresent());
	}
	
	@Test
	public void TestBuscaPorIdEventoPaginavel() {
		Page<Equipe> c = this.eS.BuscarEquipePorIdEventoPaginavel(id, new PageRequest(0,10));
		assertNotNull(c);
	}
}
