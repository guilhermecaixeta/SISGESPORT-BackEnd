package com.ifg.sistema.sisgesport.api.repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.List;

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

import com.ifg.sistema.sisgesport.api.entities.Curso;
import com.ifg.sistema.sisgesport.api.entities.Instituicao;
import com.ifg.sistema.sisgesport.api.entities.Turma;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class TurmaRepositorioTeste {
	@Autowired
	private TurmaRepositorio tR;
	@Autowired
	private CursoRepositorio cR;
	@Autowired
	private InstituicaoRepositorio iR;
	
	private static final Instituicao instituto = carregarInstituto();
	private static final Curso curso = carregarCurso();
	private static final Turma turma = carregarTurma();
	private static Integer id_turma = 0;
	
	@Before
	public void setUp() throws Exception{
		iR.save(instituto);
		cR.save(curso);
		Turma turma = new Turma();
		turma.setData_inicial_turma(Calendar.getInstance());
		turma.setData_limite(Calendar.getInstance());
		turma.setFlg_ativo(true);
		turma.setNome("20122/TADS");
		turma.setCurso(curso);
		tR.save(turma);
		id_turma = turma.getId();
	}
	
	@After
	public final void tearDown() {
		this.tR.deleteAll();
	}
	
	@Test
	public void testBuscarPorId() {
		Turma t = tR.findById(id_turma);
		
		assertNotNull(t);
	}

	@Test
	public void testBuscarPorCurso() {
		List<Turma> lista = tR.findByCursoId(curso.getId());
		
		assertEquals(1, lista.size());
	}
	
	@Test
	public void testBuscarPorCursoPaginacao() {
		PageRequest page = new PageRequest(0, 10);
		Page<Turma> lista = tR.findByCursoId(curso.getId(), page);
		
		assertEquals(1, lista.getNumberOfElements());
	}
	
	private static Instituicao carregarInstituto() {
		Instituicao instituto = new Instituicao();
		instituto.setDescricao("IFG - Instituto Federal de Goiás - Campus Luziânia");
		instituto.setNome("IFG - Campus Luziânia.");
		return instituto;
	}
	
	private static Curso carregarCurso() {
		Curso c = new Curso();
		c.setFlg_ativo(true);
		c.setInstituicao(instituto);
		c.setNome("Tecnologia em Analise e Desenvolvimento de Sistemas");
		return c;
	}
	
	private static Turma carregarTurma() {

		return turma;
	}
}
