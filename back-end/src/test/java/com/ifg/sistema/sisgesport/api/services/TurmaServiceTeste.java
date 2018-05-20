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

import com.ifg.sistema.sisgesport.api.entities.Turma;
import com.ifg.sistema.sisgesport.api.repositorios.TurmaRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class TurmaServiceTeste {
	@MockBean
	private TurmaRepositorio tR;
	@Autowired
	private TurmaService tS;
	private static final Long id = (long)1;
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.tR.save(Mockito.any(Turma.class))).willReturn(new Turma());
		BDDMockito.given(this.tR.findOne(Mockito.anyLong())).willReturn(new Turma());
		BDDMockito.given(this.tR.findByNomeEquals(Mockito.anyString())).willReturn(new Turma());
		BDDMockito.given(this.tR.findByCursoId(Mockito.anyLong())).willReturn(new ArrayList<Turma>());
		BDDMockito.given(this.tR.findByCursoId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
		.willReturn(new PageImpl<Turma>(new ArrayList<Turma>()));
	}
	
	@Test
	public void TestPersistenciaEvento() {
		Turma e = this.tS.Salvar(new Turma());
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscaPorId() {
		Optional<Turma> e = this.tS.BuscarPorId((long)1);
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscarPorNome() {
		Optional<Turma> e = this.tS.BuscarPorNome("teste");
		assertNotNull(e);
	}
		
	@Test
	public void TestBuscarPorCursoId() {
		Optional<List<Turma>> c = this.tS.BuscarPorCursoId(id);
		assertTrue(c.isPresent());
	}
	
	@Test
	public void TestBuscarPorCursoIdPaginavel() {
		Page<Turma> c = this.tS.BuscarPorCursoIdPaginavel(id, new PageRequest(0,10));
		assertNotNull(c);
	}
}
