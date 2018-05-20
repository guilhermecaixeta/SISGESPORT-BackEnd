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

import com.ifg.sistema.sisgesport.api.entities.Posicao;
import com.ifg.sistema.sisgesport.api.repositorios.PosicaoRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class PosicaoServiceTeste {
	@MockBean
	private PosicaoRepositorio pR;
	@Autowired
	private PosicaoService pS;
	private static final Long id = (long)1;
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.pR.save(Mockito.any(Posicao.class))).willReturn(new Posicao());
		BDDMockito.given(this.pR.findOne(Mockito.anyLong())).willReturn(new Posicao());
		BDDMockito.given(this.pR.findByNomeEquals(Mockito.anyString())).willReturn(new Posicao());
		BDDMockito.given(this.pR.findByModalidadeId(Mockito.anyLong())).willReturn(new ArrayList<Posicao>());
		BDDMockito.given(this.pR.findByModalidadeId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
		.willReturn(new PageImpl<Posicao>(new ArrayList<Posicao>()));
	}
	
	@Test
	public void TestPersistenciaEvento() {
		Posicao e = this.pS.Salvar(new Posicao());
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscaPorId() {
		Optional<Posicao> e = this.pS.BuscarPorId((long)1);
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscarPorNome() {
		Optional<Posicao> e = this.pS.BuscarPorNome("teste");
		assertNotNull(e);
	}
		
	@Test
	public void TestBuscarPorModalidadeId() {
		Optional<List<Posicao>> c = this.pS.BuscarPorModalidadeId(id);
		assertTrue(c.isPresent());
	}
	
	@Test
	public void TestBuscarPorModalidadeIdPaginavel() {
		Page<Posicao> c = this.pS.BuscarPorModalidadeIdPaginavel(id, new PageRequest(0,10));
		assertNotNull(c);
	}
}
