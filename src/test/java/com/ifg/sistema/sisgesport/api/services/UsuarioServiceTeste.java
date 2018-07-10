package com.ifg.sistema.sisgesport.api.services;

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

import com.ifg.sistema.sisgesport.api.entities.commom_entities.Pessoa;
import com.ifg.sistema.sisgesport.api.repositorios.UsuarioRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class UsuarioServiceTeste {
	@Autowired
	private UsuarioService uS;
	@MockBean
	private UsuarioRepositorio uR;
	private static final String matricula = "20122080010047";
	
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.uR.findByMatricula(Mockito.anyString())).willReturn(new Pessoa());
	}
	
	@Test
	public void TestBuscarAlunoPorMatricula() {
		Optional<Pessoa> pessoa= this.uS.BuscarPorMatricula(matricula);
		
		assertTrue(pessoa.isPresent());
	}
}
