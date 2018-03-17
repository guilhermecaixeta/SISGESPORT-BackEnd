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
import com.ifg.sistema.sisgesport.api.entities.Tipo_Ponto;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class TipoPontoRepositorioTeste {

	@Autowired
	private ModalidadeRepositorio mR;
	@Autowired
	private TipoPontoRepositorio tpR;
	
	private static final Modalidade modalidade = carregarModalidade();
	private static final Tipo_Ponto ponto = carregarTipoPonto();
	
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
	public void carregarTipoPontoPaginado() {
		List<Tipo_Ponto> pp = tpR.findByModalidadeId(modalidade.getId());
		assertNotNull(pp);
	}
	
	@Test
	public void carregarTipoPontoLista() {
		Tipo_Ponto pp = tpR.findById(ponto.getId());
		assertNotNull(pp);
	}
	
	private static Modalidade carregarModalidade(){
		Modalidade mod = new Modalidade();
		List<Tipo_Ponto> lista = new ArrayList<Tipo_Ponto>();
		lista.add(ponto);
		mod.setDescricao("Esporte Coletivo de até 11 jogadores.");
		mod.setNome("Futebol");
		mod.setTipoPonto(lista);
		mod.setNum_max_jogador(21);
		mod.setNum_min_jogador(11);
		return mod;
	}
	
	private static Tipo_Ponto carregarTipoPonto() {
		Tipo_Ponto ponto = new Tipo_Ponto();
		ponto.setNome("gol");
		ponto.setValor(1);
		return ponto;
	}
}
