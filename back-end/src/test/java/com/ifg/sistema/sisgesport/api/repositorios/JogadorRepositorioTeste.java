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

import com.ifg.sistema.sisgesport.api.entities.Aluno;
import com.ifg.sistema.sisgesport.api.entities.Cargo;
import com.ifg.sistema.sisgesport.api.entities.Equipe;
import com.ifg.sistema.sisgesport.api.entities.Evento;
import com.ifg.sistema.sisgesport.api.entities.Jogador;
import com.ifg.sistema.sisgesport.api.entities.Modalidade;
import com.ifg.sistema.sisgesport.api.entities.Posicao;
import com.ifg.sistema.sisgesport.api.entities.Servidor;
import com.ifg.sistema.sisgesport.api.entities.Time;
import com.ifg.sistema.sisgesport.api.entities.TipoPonto;
import com.ifg.sistema.sisgesport.api.entities.Turma;
import com.ifg.sistema.sisgesport.api.enums.PerfilSistema;
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
	private static final TipoPonto ponto = carregarTipoPonto();
	private static final Modalidade modalidade = carregarModalidade();
	private static final Time time = carregarTime();
	private static final Posicao posicao = carregarPosicao();

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
		
		Jogador j = new Jogador();
		j.setJogador(aluno);
		j.setNumCamisa(10);
		j.setTime(time);
		j.setPosicao(posicao);
		jR.save(j);
	}
	
//	@After
//	public final void tearDown() {
//		jR.deleteAll();
//	}
	
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
		
	private static Turma carregarTurma() {
		Turma t = new Turma();
		t.setDataInicial(new Date());
		t.setDataLimite(new Date());
		t.setFlgAtivo(true);
		t.setNome("20121/TADS");
		return t;
	}
	
	private static Aluno carregarAluno() {
		Aluno aluno = new Aluno();
		aluno.setNome("Guilherme Teste");
		aluno.setDataNascimento(new Date());
		aluno.setSenha(PasswordUtils.GerarBCrypt("usuario"));
		aluno.setSexo('M');
		aluno.setMatricula(matricula);
		aluno.setTurma(turma);
		aluno.setPerfil(PerfilSistema.ROLE_USUARIO);
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
		serv.setDataNascimento(new Date());
		serv.setSenha(PasswordUtils.GerarBCrypt("usuario"));
		serv.setSexo('M');
		serv.setMatricula("20122080010047");
		serv.setCargo(cargo);
		serv.setPerfil(PerfilSistema.ROLE_ADMIN);
		return serv;
	}
	
	private static Evento CarregaEvento() {
		Evento ev = new Evento();
		ev.setDataFim(new Date());
		ev.setDataInicio(new Date());
		ev.setDataFimInscricao(new Date());
		ev.setDataInicioInscricao(new Date());
		ev.setDescricao("Evento teste");
		ev.setNome("Evento de Teste");
		ev.setQntEquipes(3);
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
	
	private static TipoPonto carregarTipoPonto() {
		TipoPonto ponto = new TipoPonto();
		ponto.setNome("gol");
		ponto.setValor(1);
		return ponto;
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
	
	private static Time carregarTime() {
		Time t= new Time();
		t.setEquipe(equipe);
		t.setModalidade(modalidade);
		t.setNumDerrota(0);
		t.setNumEmpate(0);
		t.setNumVitoria(0);
		t.setPontuacao(0);
		return t;
	}
}
