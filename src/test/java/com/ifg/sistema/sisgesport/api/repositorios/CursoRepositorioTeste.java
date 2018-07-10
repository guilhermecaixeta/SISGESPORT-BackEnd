package com.ifg.sistema.sisgesport.api.repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

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

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class CursoRepositorioTeste {

	@Autowired
	private CursoRepositorio cR;
	
	@Autowired
	private InstituicaoRepositorio iR;
	
	private static final Instituicao instituto = carregarInstituto();
	private static final Instituicao instituto2 = carregarInstituto2();
	
	private static final Curso curso = carregarCurso();
	private static final Curso curso2= carregarCurso2();
	
	@Before
	public void setUp() throws Exception{
		iR.save(instituto);
		iR.save(instituto2);

		curso.setInstituicao(instituto);
		cR.save(curso);
		
		curso2.setInstituicao(instituto2);
		cR.save(curso2);
	}
	
//	@After
//	public final void tearDown() {
//		cR.deleteAll();
//	}
	
	@Test
	public void TesteBuscarPorInstituto() {
		List<Curso> lC = this.cR.findByInstituicaoId(instituto.getId());
		
		assertNotNull(lC);
	}
	
	@Test
	public void TesteBuscarPorInstitutoPaginacao() {
		PageRequest page = new PageRequest(0, 10);
		Page<Curso> lC = this.cR.findByInstituicaoId(instituto.getId(), page);
		
		assertEquals(1, lC.getNumberOfElements());
	}	
	
	private static Instituicao carregarInstituto() {
		Instituicao instituto = new Instituicao();
		instituto.setDescricao("IFG - Instituto Federal de Goiás - Campus Luziânia");
		instituto.setNome("IFG - Campus Inhumas.");
		return instituto;
	}
	
	private static Instituicao carregarInstituto2() {
		Instituicao instituto = new Instituicao();
		instituto.setDescricao("IFG - Instituto Federal de Goiás - Campus Luziânia");
		instituto.setNome("IFG - Campus Valparáiso.");
		return instituto;
	}
	public static Curso carregarCurso() {
		Curso c = new Curso();
		c.setFlg_ativo(true);
		c.setNome("Tecnologia em Analise e Desenvolvimento de Sistemas");
		return c;
	}
	
	public static Curso carregarCurso2() {
		Curso c2 = new Curso();
		c2.setFlg_ativo(true);
		c2.setNome("Bacharelado em Sistemas da Informação");
		return c2;
	}
}
