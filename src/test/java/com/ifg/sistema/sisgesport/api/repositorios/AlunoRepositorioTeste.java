package com.ifg.sistema.sisgesport.api.repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ifg.sistema.sisgesport.api.entities.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AlunoRepositorioTeste {

	@Autowired
	private AlunoRepositorio alunoRepositorio;
	@Autowired
	private TurmaRepositorio tR;
	
	private static final String matricula ="20122080010047";
	private static final Turma turma = turmaAluno();
	@Before
	public void setUp() throws Exception{
		Aluno aluno = new Aluno();
		tR.save(turma);
		aluno.setNome("Guilherme");
		aluno.setData_nasc(Calendar.getInstance());
		aluno.setLogin("usuario");
		aluno.setSenha("usuario");
		aluno.setSexo('M');
		aluno.setMatricula(matricula);
		aluno.setTurma(turma);
		this.alunoRepositorio.save(aluno);
		
		Aluno aluno2 = new Aluno();
		tR.save(turma);
		aluno2.setNome("user");
		aluno2.setData_nasc(Calendar.getInstance());
		aluno2.setLogin("user");
		aluno2.setSenha("user");
		aluno2.setSexo('F');
		aluno2.setMatricula("20122080010042");
		aluno2.setTurma(turma);
		this.alunoRepositorio.save(aluno2);
	}
	
	@After
	public final void tearDown() {
		this.alunoRepositorio.deleteAll();
	}
	
	@Test
	public void testBuscarPorMatricula() {
		Aluno aluno = this.alunoRepositorio.findByMatricula(matricula);
		
		assertEquals(matricula, aluno.getMatricula());
	}
	
	@Test
	public void testBuscarPorTurma() {
		List<Aluno> aluno = this.alunoRepositorio.findByTurmaId(turma.getId());
		List<Aluno> compare = new ArrayList<>();
		compare.add( this.alunoRepositorio.findByMatricula(matricula));
		compare.add( this.alunoRepositorio.findByMatricula("20122080010042"));
		int num1 =compare.size();
		int num2 = aluno.size();
		
		assertEquals(num1, num2);
		assertNotNull(aluno);
	}
	
	private static Turma turmaAluno() {
		Turma t = new Turma();
		t.setData_inicial_turma(Calendar.getInstance());
		t.setData_limite(Calendar.getInstance());
		t.setFlg_ativo(true);
		t.setNome("20122/TADS");
		return t;
	}
}
