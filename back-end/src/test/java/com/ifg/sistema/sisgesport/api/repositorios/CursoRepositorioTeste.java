package com.ifg.sistema.sisgesport.api.repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
	
	@Before
	public void setUp() throws Exception{
		iR.save(instituto);
		iR.save(instituto2);
		Curso c = new Curso();
		c.setFlg_ativo(true);
		c.setInstituicao(instituto);
		c.setNome("Tecnologia em Analise e Desenvolvimento de Sistemas");
		cR.save(c);
		
		Curso c2 = new Curso();
		c2.setFlg_ativo(true);
		c2.setInstituicao(instituto2);
		c2.setNome("Bacharelado em Sistemas da Informação");
		cR.save(c2);
	}
	
	@After
	public final void tearDown() {
		cR.deleteAll();
	}
	
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
		instituto.setNome("IFG - Campus Luziânia.");
		return instituto;
	}
	
	private static Instituicao carregarInstituto2() {
		Instituicao instituto = new Instituicao();
		instituto.setDescricao("IFG - Instituto Federal de Goiás - Campus Luziânia");
		instituto.setNome("IFG - Campus Luziânia.");
		return instituto;
	}
}
