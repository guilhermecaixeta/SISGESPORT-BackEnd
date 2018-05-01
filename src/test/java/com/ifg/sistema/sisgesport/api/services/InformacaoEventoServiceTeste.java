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

import com.ifg.sistema.sisgesport.api.entities.InformacaoEvento;
import com.ifg.sistema.sisgesport.api.repositorios.InformacaoEventoRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class InformacaoEventoServiceTeste {
	@MockBean
	private InformacaoEventoRepositorio eR;
	@Autowired
	private InformacaoEventoService eS;
	private static final String codEvento = "teste";
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.eR.save(Mockito.any(InformacaoEvento.class))).willReturn(new InformacaoEvento());
		BDDMockito.given(this.eR.findOne(Mockito.anyLong())).willReturn(new InformacaoEvento());
		BDDMockito.given(this.eR.findByEventoCodigoEvento(Mockito.anyString())).willReturn(new ArrayList<InformacaoEvento>());
		BDDMockito.given(this.eR.findByEventoCodigoEvento(Mockito.anyString(), Mockito.any(PageRequest.class)))
		.willReturn(new PageImpl<InformacaoEvento>(new ArrayList<InformacaoEvento>()));
	}
	
	@Test
	public void TestPersistenciaEvento() {
		InformacaoEvento e = this.eS.Salvar(new InformacaoEvento());
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscaPorId() {
		Optional<InformacaoEvento> e = this.eS.BuscarPorId((long)1);
		assertNotNull(e);
	}
		
	@Test
	public void TestBuscaPorMatriculaCriador() {
		Optional<List<InformacaoEvento>> c = this.eS.BuscarPorCodigoEvento(codEvento);
		assertTrue(c.isPresent());
	}
	
	@Test
	public void TestBuscaPorMatriculaCriadorPaginavel() {
		Page<InformacaoEvento> c = this.eS.BuscarPorCodigoEventoPaginavel(codEvento, new PageRequest(0,10));
		assertNotNull(c);
	}
}
