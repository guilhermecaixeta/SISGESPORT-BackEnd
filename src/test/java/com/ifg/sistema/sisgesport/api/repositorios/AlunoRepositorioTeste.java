package com.ifg.sistema.sisgesport.api.repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

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
import com.ifg.sistema.sisgesport.api.enums.PerfilSistema;
import com.ifg.sistema.sisgesport.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class AlunoRepositorioTeste {

	@Autowired
	private AlunoRepositorio aR;
	@Autowired
	private TurmaRepositorio tR;

	private static final String matricula = "20122080010054";
	private static final Turma turma = carregarturma();
	private static final String email = "alunoNovo@ifg.com.br";
	private static final Aluno aluno = carregarAluno();
	private static final Aluno aluno2 = carregarAluno2();

	@Before
	public void setUp() throws Exception {
		tR.save(turma);
		aluno.setTurma(turma);
		this.aR.save(aluno);

		aluno2.setTurma(turma);
		this.aR.save(aluno2);
	}

//	@After
//	public final void tearDown() {
//		this.aR.deleteAll();
//	}

	@Test
	public void testBuscarPorTurma() {
		PageRequest page = new PageRequest(0, 10);
		Page<Aluno> aluno = this.aR.findByTurmaId(turma.getId(), page);

		assertEquals(2, aluno.getNumberOfElements());
		assertNotNull(aluno);
	}

	@Test
	public void testBuscarPorMatricula() {
		Aluno a = this.aR.findByMatricula(matricula);
		assertNotNull(a);
		assertEquals(matricula, a.getMatricula());
	}

	@Test
	public void testBuscarPorId() {
		Aluno a = this.aR.findByNome("Guilherme");
		assertEquals(matricula, a.getMatricula());
	}

	@Test
	public void testBuscarPorEmail() {
		Aluno a = this.aR.findByEmail(email);
		assertNotNull(a);
		assertEquals(email, a.getEmail());
	}

	private static Turma carregarturma() {
		Turma t = new Turma();
		t.setDataInicial(new Date());
		t.setDataLimite(new Date());
		t.setFlgAtivo(true);
		t.setNome("20122/TADS");
		return t;
	}

	private static Aluno carregarAluno() {
		Aluno aluno = new Aluno();
		aluno.setNome("Guilherme");
		aluno.setDataNascimento(new Date());
		aluno.setSenha(PasswordUtils.GerarBCrypt("usuario"));
		aluno.setSexo('M');
		aluno.setPerfil(PerfilSistema.ROLE_USUARIO);
		aluno.setMatricula(matricula);
		aluno.setEmail(email);
		return aluno;
	}
	
	private static Aluno carregarAluno2() {
		Aluno aluno2 = new Aluno();
		aluno2.setNome("user");
		aluno2.setEmail("aluno2@gmail.com");
		aluno2.setDataNascimento(new Date());
		aluno2.setSenha(PasswordUtils.GerarBCrypt("201220800"));
		aluno2.setSexo('F');
		aluno2.setMatricula("20131100010042");
		aluno2.setPerfil(PerfilSistema.ROLE_USUARIO);
		return aluno2;
	}
}
