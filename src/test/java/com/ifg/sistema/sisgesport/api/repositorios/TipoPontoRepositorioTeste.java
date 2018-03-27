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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.ifg.sistema.sisgesport.api.entities.Modalidade;
import com.ifg.sistema.sisgesport.api.entities.TipoPonto;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class TipoPontoRepositorioTeste {

	@Autowired
	private ModalidadeRepositorio mR;
	@Autowired
	private TipoPontoRepositorio tpR;
	
	private static final Modalidade modalidade = carregarModalidade();
	private static final TipoPonto ponto = carregarTipoPonto();
	
	@Before
	public void setUp() throws Exception{
		tpR.save(ponto);
		mR.save(modalidade);
	}
	
	@After
	public final void tearDown() {
		tpR.deleteAll();
	}
	
	@Test
	public void carregarTipoPontoLista() {
		List<TipoPonto> pp = tpR.findByModalidadeId(modalidade.getId());
		assertNotNull(pp);
	}
	
	@Test
	public void carregarTipoPontoPaginado() {
		PageRequest page = new PageRequest(0, 10);
		Page<TipoPonto> pp = tpR.findByModalidadeId(modalidade.getId(), page);
		assertNotNull(pp);
	}
	
	@Test
	public void carregarTipoPontoNome() {
		TipoPonto pp = tpR.findByNome("gol");
		assertNotNull(pp);
	}
	
	private static Modalidade carregarModalidade(){
		Modalidade mod = new Modalidade();
		List<TipoPonto> lista = new ArrayList<TipoPonto>();
		lista.add(ponto);
		mod.setDescricao("Esporte Coletivo de até 11 jogadores.");
		mod.setNome("Futebol");
		mod.setTipoPonto(lista);
		mod.setNumMaxJogador(21);
		mod.setNumMinJogador(11);
		return mod;
	}
	
	private static TipoPonto carregarTipoPonto() {
		TipoPonto ponto = new TipoPonto();
		ponto.setNome("gol");
		ponto.setValor(1);
		return ponto;
	}
}
