package com.ifg.sistema.sisgesport.api.repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Calendar;
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

import com.ifg.sistema.sisgesport.api.entities.Aluno;
import com.ifg.sistema.sisgesport.api.entities.Cargo;
import com.ifg.sistema.sisgesport.api.entities.Equipe;
import com.ifg.sistema.sisgesport.api.entities.Evento;
import com.ifg.sistema.sisgesport.api.entities.Jogador;
import com.ifg.sistema.sisgesport.api.entities.Modalidade;
import com.ifg.sistema.sisgesport.api.entities.Posicao;
import com.ifg.sistema.sisgesport.api.entities.Servidor;
import com.ifg.sistema.sisgesport.api.entities.Time;
import com.ifg.sistema.sisgesport.api.entities.Tipo_Ponto;
import com.ifg.sistema.sisgesport.api.entities.Turma;
import com.ifg.sistema.sisgesport.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class JogadorRepositorioTeste {

	@Autowired
	private JogadorRepositorio jR;
	@Autowired
	private AlunoRepositorio aR;
	@Autowired
	private TurmaRepositorio tR;
	@Autowired
	private TimeRepositorio tmR;
	@Autowired
	private EquipeRepositorio eR;
	@Autowired
	private EventoRepositorio evR;
	@Autowired
	private ServidorRepositorio svR;
	@Autowired
	private CargoRepositorio crR;
	@Autowired
	private ModalidadeRepositorio mR;
	@Autowired
	private TipoPontoRepositorio tpR;
	@Autowired
	private PosicaoRepositorio psR;
	
	private static final Turma turma = carregarTurma();
	private static final String matricula ="20122080010047";
	private static final Aluno aluno = carregarAluno();
	private static final Cargo cargo = carregarCargo();
	private static final Servidor servidor = carregaServidor();
	private static final Evento evento = CarregaEvento();
	private static final Equipe equipe = carregarEquipe();
	private static final Tipo_Ponto ponto = carregarTipoPonto();
	private static final Modalidade modalidade = carregarModalidade();
	private static final Time time = carregarTime();
	private static final Posicao posicao = carregarPosicao();
	private static final Jogador jogador = carregarJogador();

	@Before
	public void setUp() throws Exception{
		tR.save(turma);
		aR.save(aluno);
		crR.save(cargo);
		svR.save(servidor);
		evR.save(evento);
		eR.save(equipe);
		tpR.save(ponto);
		mR.save(modalidade);
		tmR.save(time);
		psR.save(posicao);
		jR.save(jogador);
	}
	
	@After
	public final void tearDown() {
		jR.deleteAll();
	}
	
	@Test
	public void testBuscarporEquipeId() {
		List<Jogador> listaJogador = this.jR.findByTimeEquipeId(equipe.getId());
		
		assertNotNull(listaJogador);
	}

	@Test
	public void testBuscarporEquipeIdPaginavel() {
		PageRequest page = new PageRequest(0, 10);
		Page<Jogador> listaJogador = this.jR.findByTimeEquipeId(equipe.getId(), page);
		
		assertEquals(1, listaJogador.getTotalElements());
	}
	
	@Test
	public void testBuscarporTimeId() {
		List<Jogador> listaJogador = this.jR.findByTimeId(time.getId());
		
		assertNotNull(listaJogador);
	}

	@Test
	public void testBuscarporTimeIdPaginavel() {
		PageRequest page = new PageRequest(0, 10);
		Page<Jogador> listaJogador = this.jR.findByTimeId(time.getId(), page);
		
		assertEquals(1, listaJogador.getTotalElements());
	}
	
//	@Test
//	public void testBuscarPorId() {
//		Jogador j = this.jR.findById(aluno.getId());
//		
//		assertNotNull(j);
//	}
	
	private static Turma carregarTurma() {
		Turma t = new Turma();
		t.setData_inicial_turma(Calendar.getInstance());
		t.setData_limite(Calendar.getInstance());
		t.setFlg_ativo(true);
		t.setNome("20121/TADS");
		return t;
	}
	
	private static Aluno carregarAluno() {
		Aluno aluno = new Aluno();
		aluno.setNome("Guilherme Teste");
		aluno.setData_nasc(new Date());
		aluno.setLogin("GuilhermeTesTe");
		aluno.setSenha(PasswordUtils.GerarBCrypt("usuario"));
		aluno.setSexo('M');
		aluno.setMatricula(matricula);
		aluno.setTurma(turma);
		return aluno;
		}
	private static Cargo carregarCargo() {
		Cargo c = new Cargo();
		c.setDescricao("Lecionar aulas");
		c.setNome("Professor");
		return c;
	}
	
	private static Servidor carregaServidor() {
		Servidor serv = new Servidor();
		serv.setNome("Guilherme");
		serv.setData_nasc(new Date());
		serv.setLogin("usuario");
		serv.setSenha(PasswordUtils.GerarBCrypt("usuario"));
		serv.setSexo('M');
		serv.setMatricula_siap("20122089010047");
		serv.setCargo(cargo);
		serv.setAdmin_sistema(true);
		return serv;
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
		mod.setTipoPonto(lista);
		mod.setNum_max_jogador(21);
		mod.setNum_min_jogador(11);
		return mod;
	}
	
	private static Posicao carregarPosicao() {
		Posicao p = new Posicao();
		p.setDescricao("Ataca o gol do outro time");
		p.setNome("Atacante");
		p.setNum_max_jogador(5);
		p.setNum_min_jogador(1);
		List<Modalidade> lM = new ArrayList<Modalidade>();
		lM.add(modalidade);
		p.setModalidade(lM);
		return p;
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
	
	private static Jogador carregarJogador() {
		Jogador j = new Jogador();
		j.setJogador(aluno);
		j.setNum_camisa(10);
		j.setTime(time);
		j.setPosicao(posicao);
		return j;
	}
}
