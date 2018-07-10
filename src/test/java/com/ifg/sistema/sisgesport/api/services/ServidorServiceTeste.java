package com.ifg.sistema.sisgesport.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ifg.sistema.sisgesport.api.entities.Servidor;
import com.ifg.sistema.sisgesport.api.repositorios.ServidorRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class ServidorServiceTeste {

	@MockBean
	private ServidorRepositorio sR;
	
	@Autowired
	private ServidorService sS;
	
	private static final Long id= (long) 1;
	private static final String matricula = "2014520800100993";
	
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.sR.save(Mockito.any(Servidor.class))).willReturn(new Servidor());
		BDDMockito.given(this.sR.findByMatricula(Mockito.anyString())).willReturn(new Servidor());
		BDDMockito.given(this.sR.findByCargoId(Mockito.anyLong())).willReturn(new ArrayList<Servidor>());
		BDDMockito.given(this.sR.findByEmail(Mockito.anyString())).willReturn(new Servidor());
		BDDMockito.given(this.sR.findByCargoId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
		.willReturn(new PageImpl<Servidor>(new ArrayList<Servidor>()));
		BDDMockito.given(this.sR.findByCargoInstituicaoId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
		.willReturn(new PageImpl<Servidor>(new ArrayList<Servidor>()));
	}
	
	@Test
	public void TestBuscarPorMatriculaSiap() {
		Optional<Servidor> aluno = this.sS.BuscarPorMatriculaSiap(matricula);
		
		assertTrue(aluno.isPresent());
	}
	
	@Test
	public void TestBuscarPorCargoId() {
		Optional<List<Servidor>> aluno = this.sS.BuscarPorCargoId(id);
		
		assertTrue(aluno.isPresent());
	}
	
	@Test
	public void TestBuscarPorEmail() {
		Optional<Servidor> aluno = this.sS.BuscarPorEmail("teste.com.br");
		
		assertTrue(aluno.isPresent());
	}
	
	@Test
	public void TestBuscarPorCargoIdPaginavel() {
		Page<Servidor> aluno = this.sS.BuscarPorCargoIdPaginavel(id, new PageRequest(0,10));
		
		assertNotNull(aluno);
	}
	
	@Test
	public void TestBuscarPorCargoInstituicaoIdPaginavel() {
		Page<Servidor> aluno = this.sS.BuscarPorCargoInstituicaoIdPaginavel(id, new PageRequest(0,10));
		
		assertNotNull(aluno);
	}
	
	@Test
	public void TestPersistencisServidor() {
		Servidor aluno = this.sS.Salvar(new Servidor());
		
		assertNotNull(aluno);
	}
}
