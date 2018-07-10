package com.ifg.sistema.sisgesport.api.repositorios;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ifg.sistema.sisgesport.api.entities.Modalidade;
import com.ifg.sistema.sisgesport.api.entities.TipoPonto;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class ModalidadeRepositorioTeste {

	@Autowired
	private ModalidadeRepositorio mR;
	@Before
	public void setUp() throws Exception{
		Modalidade mod = new Modalidade();
		TipoPonto ponto = new TipoPonto();
		ponto.setNome("cesta");
		ponto.setValor(1);
		ponto.setId(null);
		List<TipoPonto> lista = new ArrayList<TipoPonto>();
		lista.add(ponto);
		mod.setDescricao("Esporte Coletivo de até 11 jogadores.");
		mod.setNome("Basquete");
		mod.setTipoPonto(lista);
		mod.setNumMaxJogador(21);
		mod.setNumMinJogador(11);
		mR.save(mod);
	}
	
	@After
	public final void tearDown() {
		mR.deleteAll();
	}
	
	@Test
	public void testBuscarPorNome() {
		Modalidade mod = mR.findByNome("Basquete");
		
		assertNotNull(mod);
	}
	
	@Test
	public void testContainsPonto() {
		Modalidade mod = mR.findByNome("Basquete");
		
		assertNotNull(mod.getTipoPonto());
	}
}
