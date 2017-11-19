package com.ifg.sistema.sisgesport.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ifg.sistema.sisgesport.api.entities.Aluno;
import com.ifg.sistema.sisgesport.api.repositorios.AlunoRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class AlunoServiceTeste {

	@MockBean
	private AlunoRepositorio alunoRep;
	
	@Autowired
	private AlunoService alunoService;
	
	private static final String matricula = "20122080010047";
	
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.alunoRep.findByMatricula(Mockito.anyString())).willReturn(new Aluno());
		BDDMockito.given(this.alunoRep.save(Mockito.any(Aluno.class))).willReturn(new Aluno());
	}
	
	@Test
	public void TestBuscarAlunoPorMatricula() {
		Optional<Aluno> aluno = this.alunoService.BuscarPorMatricula(matricula);
		
		assertTrue(aluno.isPresent());
	}
	
	@Test
	public void TestPersistenciaAluno() {
		Aluno aluno = this.alunoService.Salvar(new Aluno());
		
		assertNotNull(aluno);
	}
}
