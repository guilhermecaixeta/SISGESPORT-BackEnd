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
import com.ifg.sistema.sisgesport.api.entities.Penalidade;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class PenalidadeRepositorioTeste {
	
	@Autowired
	private PenalidadeRepositorio pR;
	@Autowired
	private ModalidadeRepositorio mR;
	private static final Modalidade modal = carregarModalidade();
	
	@Before
	public void setUp() throws Exception{
		mR.save(modal);
		Penalidade p = new Penalidade();
		List<Modalidade> lista = new ArrayList<Modalidade>();
		lista.add(modal);
		p.setModalidade(lista);
		p.setDescricao("Falta grave");
		p.setNome("Cartão Vermelho");
		pR.save(p);
	}
	
	@After
	public final void tearDown() {
		pR.deleteAll();
	}
	
	@Test
	public void TesteBuscarPorNome(){
		Penalidade p = pR.findByNome("Cartão Vermelho");
		
		assertNotNull(p);
	}
	
	@Test
	public void TesteBuscarPorModalidade(){
		List<Penalidade> p = pR.findByModalidadeId(modal.getId());
		
		assertNotNull(p);
	}
	
	public static Modalidade carregarModalidade() {
		Modalidade mod = new Modalidade();
		mod.setDescricao("Esporte Coletivo de até 11 jogadores.");
		mod.setNome("Futebol");
		mod.setNumMaxJogador(21);
		mod.setNumMinJogador(11);
		return mod;
	}
}
