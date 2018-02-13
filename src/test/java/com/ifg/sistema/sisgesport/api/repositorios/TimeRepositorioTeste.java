package com.ifg.sistema.sisgesport.api.repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
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

import com.ifg.sistema.sisgesport.api.entities.Cargo;
import com.ifg.sistema.sisgesport.api.entities.Equipe;
import com.ifg.sistema.sisgesport.api.entities.Evento;
import com.ifg.sistema.sisgesport.api.entities.Modalidade;
import com.ifg.sistema.sisgesport.api.entities.Servidor;
import com.ifg.sistema.sisgesport.api.entities.Time;
import com.ifg.sistema.sisgesport.api.entities.Tipo_Ponto;
import com.ifg.sistema.sisgesport.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class TimeRepositorioTeste {
	@Autowired
	private TimeRepositorio tmR;
	@Autowired
	private EquipeRepositorio eR;
	@Autowired
	private EventoRepositorio eventoRepositorio;
	@Autowired
	private ServidorRepositorio servidorRepositorio;
	@Autowired
	private CargoRepositorio cargoRepositorio;
	@Autowired
	private ModalidadeRepositorio mR;
	@Autowired
	private TipoPontoRepositorio tpR;
	
	private static final Evento evento = CarregaEvento();
	private static final Cargo cargo = cargoServidor();
	private static final Servidor servidor = carregaServidor();
	private static final Equipe equipe = carregarEquipe();
	private static final Modalidade modalidade = carregarModalidade();
	private static final Tipo_Ponto ponto = carregarTipoPonto();
	private static final Time time = carregarTime();
	
	@Before
	public void setUp() throws Exception{
		cargoRepositorio.save(cargo);
		servidorRepositorio.save(servidor);
		eventoRepositorio.save(evento);
		eR.save(equipe);
		tpR.save(ponto);
		mR.save(modalidade);
		tmR.save(time);
	}
	
	@After
	public final void tearDown() {
		tmR.deleteAll();
	}
	
	@Test
	public void testBuscarPorId() {
		Time t = this.tmR.findById(time.getId());
		
		assertNotNull(t);
	}
	
	@Test
	public void testBuscarporEquipeId() {
		List<Time> eqp = this.tmR.findByEquipeId(equipe.getId());
		
		assertNotNull(eqp);
	}
	
	@Test
	public void testBuscarporEquipeIdPaginavel() {
		PageRequest page = new PageRequest(0, 10);
		Page<Time> listatime = this.tmR.findByEquipeId(equipe.getId(), page);
		
		assertEquals(1, listatime.getTotalElements());
	}
	
	private static Servidor carregaServidor() {
		Servidor serv = new Servidor();
		serv.setNome("Guilherme");
		serv.setData_nasc(new Date());
		serv.setLogin("usuario");
		serv.setSenha(PasswordUtils.GerarBCrypt("usuario"));
		serv.setSexo('M');
		serv.setMatricula_siap("20122080010047");
		serv.setCargo(cargo);
		serv.setAdmin_sistema(true);
		return serv;
	}
	
	private static Cargo cargoServidor() {
		Cargo c = new Cargo();
		c.setDescricao("Lecionar aulas");
		c.setNome("Professor");
		return c;
	}
	
	private static Evento CarregaEvento() {
		Evento ev = new Evento();
		ev.setData_fim_inscricao(new Date());
		ev.setData_inicio_inscricao(new Date());
		ev.setData_fim(new Date());
		ev.setData_inicio(new Date());
		ev.setDescricao("Evento teste");
		ev.setNome("Evento de Teste");
		ev.setQnt_equipes(3);
		ev.setCriador(servidor);
		return ev;
	}
	
	private static Equipe carregarEquipe() {
		Equipe eqp = new Equipe();
		eqp.setCor("AEIOU");
		eqp.setEvento(evento);
		eqp.setNome("Equipe um");
		return eqp; 
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
	
	private static Time carregarTime() {
		Time t= new Time();
		t.setEquipe(equipe);
		t.setModalidade(modalidade);
		t.setNum_derrota(0);
		t.setNum_empate(0);
		t.setNum_vitoria(0);
		t.setPontuacao(0);
		return t;
	}
}
