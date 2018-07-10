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

import com.ifg.sistema.sisgesport.api.entities.Instituicao;
import com.ifg.sistema.sisgesport.api.repositorios.InstituicaoRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class InstituicaoServiceTeste {
	@MockBean
	private InstituicaoRepositorio iR;
	@Autowired
	private InstituicaoService iS;
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.iR.save(Mockito.any(Instituicao.class))).willReturn(new Instituicao());
		BDDMockito.given(this.iR.findOne(Mockito.anyLong())).willReturn(new Instituicao());
		BDDMockito.given(this.iR.findByNome(Mockito.anyString())).willReturn(new Instituicao());
	}
	
	@Test
	public void TestPersistenciaInstituicao() {
		Instituicao e = this.iS.Salvar(new Instituicao());
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscaPorId() {
		Optional<Instituicao> e = this.iS.BuscarPorId((long)1);
		assertNotNull(e);
	}
		
	@Test
	public void TestBuscaPorNomeInstituicao() {
		Optional<Instituicao> c = this.iS.BuscarPorNomeInstituicao("teste");
		assertTrue(c.isPresent());
	}
}
