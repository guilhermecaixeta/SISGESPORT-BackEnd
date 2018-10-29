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

import com.ifg.sistema.sisgesport.api.entities.Curso;
import com.ifg.sistema.sisgesport.api.repositorios.CursoRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class CursoServiceTeste {
	@MockBean
	private CursoRepositorio cR;
	
	@Autowired
	private CursoService cS;
	private static final Long id = (long)1;
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.cR.save(Mockito.any(Curso.class))).willReturn(new Curso());
		BDDMockito.given(this.cR.findOne(Mockito.anyLong())).willReturn(new Curso());
		BDDMockito.given(this.cR.findByInstituicaoId(Mockito.anyLong())).willReturn(new ArrayList<Curso>());
		BDDMockito.given(this.cR.findByInstituicaoId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
		.willReturn(new PageImpl<Curso>(new ArrayList<Curso>()));
				
	}
	
	@Test
	public void TestPersistenciaCurso() {
		Curso c = this.cS.Salvar(new Curso());
		assertNotNull(c);
	}
	
	@Test
	public void TestBuscaPorId() {
		Optional<Curso> c = this.cS.BuscarPorId(id);
		assertNotNull(c);
	}
	
	@Test
	public void TestBuscaPorIdInstituicao() {
		Optional<List<Curso>> c = this.cS.BuscarCursoPorIdInstituicao(id);
		assertTrue(c.isPresent());
	}
	
	@Test
	public void TestBuscaPorIdInstituicaoPaginavel() {
		Page<Curso> c = this.cS.BuscarCursoPorIdInstituicaoPaginavel(id, new PageRequest(0,10));
		assertNotNull(c);
	}
	
}
