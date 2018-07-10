package com.ifg.sistema.sisgesport.api.repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ifg.sistema.sisgesport.api.entities.Aluno;
import com.ifg.sistema.sisgesport.api.entities.Turma;
import com.ifg.sistema.sisgesport.api.entities.commom_entities.Pessoa;
import com.ifg.sistema.sisgesport.api.enums.PerfilSistema;
import com.ifg.sistema.sisgesport.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class UsuarioRepositorioTeste {

	@Autowired
	private AlunoRepositorio aR;
	@Autowired
	private TurmaRepositorio tR;
	@Autowired
	private UsuarioRepositorio uR;
	
	private static final String matricula = "20122080010054";
	private static final Turma turma = carregarturma();
	private static final String email = "alunoNovo@ifg.com.br";
	private static final Aluno aluno = carregarAluno();
	
	@Before
	public void setUp() throws Exception {
		tR.save(turma);
		aluno.setTurma(turma);
		this.aR.save(aluno);

	}
	
	@Test
	public void testBuscarPorMatricula() {
		Pessoa a = this.uR.findByMatricula(matricula);
		assertNotNull(a);
		assertEquals(matricula, a.getMatricula());
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
}
