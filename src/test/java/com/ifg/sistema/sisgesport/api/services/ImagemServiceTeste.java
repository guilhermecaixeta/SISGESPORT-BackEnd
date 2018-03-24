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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ifg.sistema.sisgesport.api.entities.Imagem;
import com.ifg.sistema.sisgesport.api.repositorios.ImagemRepositorio;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class ImagemServiceTeste {
	@MockBean
	private ImagemRepositorio iR;
	@Autowired
	private ImagemService iS;
	private static final Long id = (long)1;
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(this.iR.save(Mockito.any(Imagem.class))).willReturn(new Imagem());
		BDDMockito.given(this.iR.findOne(Mockito.anyLong())).willReturn(new Imagem());
		BDDMockito.given(this.iR.findByEntidadeComumId(Mockito.anyLong())).willReturn(new ArrayList<Imagem>());
		BDDMockito.given(this.iR.findByInformacaoEventoId(Mockito.anyLong())).willReturn(new ArrayList<Imagem>());
	}
	
	@Test
	public void TestPersistenciaEvento() {
		Imagem i = this.iS.Salvar(new Imagem());
		assertNotNull(i);
	}
	
	@Test
	public void TestBuscaPorId() {
		Optional<Imagem> i = this.iS.BuscarPorId((long)1);
		assertNotNull(i);
	}
		
	@Test
	public void TestBuscarPorEntidadeComumId() {
		Optional<List<Imagem>> c = this.iS.BuscarPorEntidadeComumId(id);
		assertTrue(c.isPresent());
	}
	
	@Test
	public void TestBuscarPorInformacaoEventoId() {
		Optional<List<Imagem>> c = this.iS.BuscarPorInformacaoEventoId(id);
		assertTrue(c.isPresent());
	}

}
