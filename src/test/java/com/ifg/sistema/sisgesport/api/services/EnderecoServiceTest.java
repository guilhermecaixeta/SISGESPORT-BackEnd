package com.ifg.sistema.sisgesport.api.services;

import static org.junit.Assert.assertNotNull;

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

import com.ifg.sistema.sisgesport.api.entities.Endereco;
import com.ifg.sistema.sisgesport.api.repositorios.EnderecoRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class EnderecoServiceTest {

	@MockBean
	private EnderecoRepositorio eR;
	@Autowired
	private EnderecoService eS;
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.eR.save(Mockito.any(Endereco.class))).willReturn(new Endereco());
		BDDMockito.given(this.eR.findOne(Mockito.anyLong())).willReturn(new Endereco());
	}
	
	@Test
	public void TestPersistenciaEndereco() {
		Endereco e = this.eS.Salvar(new Endereco());
		assertNotNull(e);
	}
	
	@Test
	public void TestBuscaPorId() {
		Optional<Endereco> e = this.eS.BuscarPorId((long)1);
		assertNotNull(e);
	}
}
