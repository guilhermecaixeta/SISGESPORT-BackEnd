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

import com.ifg.sistema.sisgesport.api.entities.Aluno;
import com.ifg.sistema.sisgesport.api.repositorios.AlunoRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class AlunoServiceTeste {

	@MockBean
	private AlunoRepositorio aR;
	
	@Autowired
	private AlunoService aS;
	private static final Long id= (long) 1;
	private static final String matricula = "20122080010047";
	
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.aR.save(Mockito.any(Aluno.class))).willReturn(new Aluno());
		BDDMockito.given(this.aR.findByMatricula(Mockito.anyString())).willReturn(new Aluno());
		BDDMockito.given(this.aR.findByEquipeId(Mockito.anyLong())).willReturn(new ArrayList<Aluno>());
		BDDMockito.given(this.aR.findByTurmaId(Mockito.anyLong())).willReturn(new ArrayList<Aluno>());
		BDDMockito.given(this.aR.findByEquipeId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
		.willReturn(new PageImpl<Aluno>(new ArrayList<Aluno>()));
		BDDMockito.given(this.aR.findByTurmaId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
		.willReturn(new PageImpl<Aluno>(new ArrayList<Aluno>()));;
	}
	
	@Test
	public void TestBuscarAlunoPorMatricula() {
		Optional<Aluno> aluno = this.aS.BuscarPorMatricula(matricula);
		
		assertTrue(aluno.isPresent());
	}
	
	@Test
	public void TestBuscarAlunoPorEquipe() {
		Optional<List<Aluno>> aluno = this.aS.BuscarPorIdEquipe(id);
		
		assertTrue(aluno.isPresent());
	}
	
	@Test
	public void TestBuscarAlunoPorTurma() {
		Optional<List<Aluno>> aluno = this.aS.BuscarPorIdTurma(id);
		
		assertTrue(aluno.isPresent());
	}
	
	@Test
	public void TestBuscarAlunoPorEquipePaginada() {
		Page<Aluno> aluno = this.aS.BuscarPorIdEquipePaginavel(id, new PageRequest(0,10));
		
		assertNotNull(aluno);
	}
	
	@Test
	public void TestBuscarAlunoPorTurmaPaginada() {
		Page<Aluno> aluno = this.aS.BuscarPorIdTurmaPaginavel(id, new PageRequest(0,10));
		
		assertNotNull(aluno);
	}
	
	@Test
	public void TestPersistenciaAluno() {
		Aluno aluno = this.aS.Salvar(new Aluno());
		
		assertNotNull(aluno);
	}
}
