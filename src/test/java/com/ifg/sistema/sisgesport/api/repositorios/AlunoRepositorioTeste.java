package com.ifg.sistema.sisgesport.api.repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ifg.sistema.sisgesport.api.entities.*;
import com.ifg.sistema.sisgesport.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
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
		aluno.setSenha(PasswordUtils.GerarBCrypt("usuario"));
		aluno.setSexo('M');
		aluno.setMatricula(matricula);
		aluno.setTurma(turma);
		this.alunoRepositorio.save(aluno);
		
		Aluno aluno2 = new Aluno();
		aluno2.setNome("user");
		aluno2.setData_nasc(Calendar.getInstance());
		aluno2.setLogin("user");
		aluno2.setSenha(PasswordUtils.GerarBCrypt("2012208001004220122080010042"));
		aluno2.setSexo('F');
		aluno2.setMatricula("20122080010042");
		aluno2.setTurma(turma);
		this.alunoRepositorio.save(aluno2);
	}
	
	@After
	public final void tearDown() {
		this.alunoRepositorio.deleteAll();
		this.tR.deleteAll();
	}
	
	@Test
	public void testBuscarPorMatricula() {
		Aluno aluno = this.alunoRepositorio.findByMatricula(matricula);
		
		assertEquals(matricula, aluno.getMatricula());
	}
	
	@Test
	public void testBuscarPorTurma() {
		PageRequest page = new PageRequest(0, 10);
		Page<Aluno> aluno = this.alunoRepositorio.findByTurmaId(turma.getId(), page);

		int num2 = aluno.getNumberOfElements();
		
		assertEquals(2, num2);
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
