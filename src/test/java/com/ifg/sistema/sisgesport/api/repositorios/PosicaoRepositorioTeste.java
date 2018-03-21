package com.ifg.sistema.sisgesport.api.repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
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

import com.ifg.sistema.sisgesport.api.entities.Modalidade;
import com.ifg.sistema.sisgesport.api.entities.Posicao;
import com.ifg.sistema.sisgesport.api.entities.Tipo_Ponto;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class PosicaoRepositorioTeste {
	@Autowired
	private PosicaoRepositorio psR;
	@Autowired
	private ModalidadeRepositorio mR;
	@Autowired
	private TipoPontoRepositorio tpR;
	
	private static final Modalidade modalidade = carregarModalidade();
	private static final Posicao posicao = carregarPosicao();
	private static final Tipo_Ponto ponto = carregarTipoPonto();
	
	@Before
	public void setUp() throws Exception{
		tpR.save(ponto);
		mR.save(modalidade);
		psR.save(posicao);
	}
	@After
	public final void tearDown() {
		psR.deleteAll();
	}
	
	@Test
	public void testBuscarporModalidadeId() {
		List<Posicao> listaPosicao = this.psR.findByModalidadeId(modalidade.getId());
		
		assertNotNull(listaPosicao);
	}
	
	@Test
	public void testBuscarporModalidadeIdPaginavel() {
		PageRequest page = new PageRequest(0, 10);
		Page<Posicao> listaPosicao = this.psR.findByModalidadeId(modalidade.getId(), page);
		
		assertEquals(1, listaPosicao.getTotalElements());
	}
	
	@Test
	public void testBuscarporId() {
		Posicao p = this.psR.findByNome("Atacante");
		
		assertNotNull(p);
	}
	
	private static Tipo_Ponto carregarTipoPonto() {
		Tipo_Ponto ponto = new Tipo_Ponto();
		ponto.setNome("gol");
		ponto.setValor(1);
		return ponto;
	}
	
	private static Modalidade carregarModalidade(){
		Modalidade mod = new Modalidade();
		List<Tipo_Ponto> lista = new ArrayList<Tipo_Ponto>();
		lista.add(ponto);
		mod.setDescricao("Esporte Coletivo de até 11 jogadores.");
		mod.setNome("Futebol");
		mod.setNumMaxJogador(21);
		mod.setNumMinJogador(11);
		return mod;
	}
	
	private static Posicao carregarPosicao() {
		Posicao p = new Posicao();
		p.setDescricao("Ataca o gol do outro time");
		p.setNome("Atacante");
		p.setNumMaxJogador(5);
		p.setNumMinJogador(1);
		List<Modalidade> lM = new ArrayList<Modalidade>();
		lM.add(modalidade);
		p.setModalidade(lM);
		return p;
	}
}
