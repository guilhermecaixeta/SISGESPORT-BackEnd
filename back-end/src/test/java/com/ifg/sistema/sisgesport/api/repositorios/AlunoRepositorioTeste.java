package com.ifg.sistema.sisgesport.api.repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

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
	
	private static final String matricula ="20122080010054";
	private static final Turma turma = carregarturma();
	private static final String email = "alunoNovo@ifg.com.br";
	@Before
	public void setUp() throws Exception{
		tR.save(turma);

		Aluno aluno = new Aluno();
		aluno.setNome("Guilherme");
		aluno.setDataNascimento(new Date());
		aluno.setSenha(PasswordUtils.GerarBCrypt("usuario"));
		aluno.setSexo('M');
		aluno.setMatricula(matricula);
		aluno.setTurma(turma);
		aluno.setPerfil(PerfilSistema.ROLE_USUARIO);
		aluno.setEmail(email);
		this.aR.save(aluno);

		Aluno aluno2 = new Aluno();
		aluno2.setNome("user");
		aluno2.setDataNascimento(new Date());
		aluno2.setSenha(PasswordUtils.GerarBCrypt("201220800"));
		aluno2.setSexo('F');
		aluno2.setMatricula("20131100010042");
		aluno2.setTurma(turma);
		aluno2.setPerfil(PerfilSistema.ROLE_USUARIO);

		this.aR.save(aluno2);
	}
	
	@After
	public final void tearDown() {
		this.aR.deleteAll();
	}
	
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
		assertNotNull(a);
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
}
