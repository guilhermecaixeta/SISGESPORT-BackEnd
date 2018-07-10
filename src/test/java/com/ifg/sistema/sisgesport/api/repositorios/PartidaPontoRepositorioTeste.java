package com.ifg.sistema.sisgesport.api.repositorios;

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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ifg.sistema.sisgesport.api.entities.Aluno;
import com.ifg.sistema.sisgesport.api.entities.Cargo;
import com.ifg.sistema.sisgesport.api.entities.Equipe;
import com.ifg.sistema.sisgesport.api.entities.Evento;
import com.ifg.sistema.sisgesport.api.entities.Jogador;
import com.ifg.sistema.sisgesport.api.entities.Modalidade;
import com.ifg.sistema.sisgesport.api.entities.Partida;
import com.ifg.sistema.sisgesport.api.entities.PartidaPonto;
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
public class PartidaPontoRepositorioTeste {
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
	private PartidaRepositorio pR;
	@Autowired
	private JogadorRepositorio jR;
	@Autowired
	private AlunoRepositorio aR;
	@Autowired
	private TurmaRepositorio tR;
	@Autowired
	private PosicaoRepositorio psR;
	@Autowired
	private PartidaPontoRepositorio ppR;	
	private static final Turma turma = carregarTurma();
	private static final String matricula ="20122080010021";
	private static final Aluno aluno = carregarAluno();
	private static final Evento evento = carregaEvento();
	private static final Cargo cargo = cargoServidor();
	private static final Servidor servidor = carregaServidor();
	private static final Equipe equipeCasa = carregarEquipeCasa();
	private static final Equipe equipeVisita = carregarEquipeVisita();
	private static final Modalidade modalidade = carregarModalidade();
	private static final TipoPonto ponto = carregarTipoPonto();
	private static final Time timeCasa = carregarTimeCasa();
	private static final Time timeVisita = carregarTimeVisita();
	private static final Posicao posicao = carregarPosicao();
	private static final Jogador jogador = carregarJogador();
	private static final Partida partida = carregarPartida();
	private static final PartidaPonto partidaPenalidade = carregarPartidadePonto();
	@Before
	public void setUp() throws Exception{
		mR.save(modalidade);
		tR.save(turma);
		aR.save(aluno);
		crR.save(cargo);
		svR.save(servidor);
		evR.save(evento);
		eR.save(equipeCasa);
		eR.save(equipeVisita);
		tmR.save(timeCasa);
		tmR.save(timeVisita);
		psR.save(posicao);
		jR.save(jogador);
		tpR.save(ponto);
		pR.save(partida);
		ppR.save(partidaPenalidade);
	}
	
	@After
	public final void tearDown() {
		ppR.deleteAll();
	}
	
	@Test
	public void carregarPartidaPenalidadeTipoPontoId() {
		List<PartidaPonto> pp = ppR.findByTipoPontoId(ponto.getId());
		assertNotNull(pp);
	}
	
	@Test
	public void carregarPartidaPenalidadeJogadorId() {
		List<PartidaPonto> pp = ppR.findByJogadorId(jogador.getId());
		assertNotNull(pp);
	}
	
	@Test
	public void carregarPartidaPenalidadePartidaId() {
		List<PartidaPonto> pp = ppR.findByPartidaId(partida.getId());
		assertNotNull(pp);
	}
	
	private static Servidor carregaServidor() {
		Servidor serv = new Servidor();
		serv.setNome("Guilherme");
		serv.setDataNascimento(new Date());
		serv.setSenha(PasswordUtils.GerarBCrypt("usuario"));
		serv.setSexo('M');
		serv.setMatricula("20122080010020");
		serv.setCargo(cargo);
		serv.setPerfil(PerfilSistema.ROLE_ADMIN);
		return serv;
	}
	
	private static Cargo cargoServidor() {
		Cargo c = new Cargo();
		c.setDescricao("Lecionar aulas");
		c.setNome("Professor de Física");
		return c;
	}
	
	private static Turma carregarTurma() {
		Turma t = new Turma();
		t.setDataInicial(new Date());
		t.setDataLimite(new Date());
		t.setFlgAtivo(true);
		t.setNome("20122/TADS");
		return t;
	}
	
	private static Aluno carregarAluno() {
		Aluno aluno = new Aluno();
		aluno.setNome("Aluno Teste 3");
		aluno.setDataNascimento(new Date());
		aluno.setSenha(PasswordUtils.GerarBCrypt("usuario"));
		aluno.setSexo('M');
		aluno.setMatricula(matricula);
		aluno.setTurma(turma);
		aluno.setPerfil(PerfilSistema.ROLE_USUARIO);
		return aluno;
		}
	
	private static Evento carregaEvento() {
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
	
	private static Equipe carregarEquipeCasa() {
		Equipe eqp = new Equipe();
		eqp.setCor("Vermelho");
		eqp.setEvento(evento);
		eqp.setNome("Equipe um");
		return eqp; 
	}
	
	private static Equipe carregarEquipeVisita() {
		Equipe eqp = new Equipe();
		eqp.setCor("Azul");
		eqp.setEvento(evento);
		eqp.setNome("Equipe dois");
		return eqp; 
	}
	
	
	private static Modalidade carregarModalidade(){
		Modalidade mod = new Modalidade();
		mod.setDescricao("Esporte Coletivo de até 11 jogadores.");
		mod.setNome("Futebol");
		mod.setNumMaxJogador(21);
		mod.setNumMinJogador(11);
		return mod;
	}
	
	private static TipoPonto carregarTipoPonto() {
		TipoPonto p= new TipoPonto();
		List<Modalidade> lista = new ArrayList<Modalidade>();
		lista.add(modalidade);
		p.setModalidade(lista);
		p.setValor(1);
		p.setNome("gol");
		return p;
	}
	
	private static Time carregarTimeCasa() {
		Time t= new Time();
		t.setEquipe(equipeCasa);
		t.setModalidade(modalidade);

		return t;
	}
	
	private static Time carregarTimeVisita() {
		Time t= new Time();
		t.setEquipe(equipeVisita);
		t.setModalidade(modalidade);
		return t;
	}
	
	public static Partida carregarPartida() {
		Partida p = new Partida();
		p.setDataPartida(new Date());
		p.setDuracaoPartida(90);
		p.setEvento(evento);
		p.setJuiz(servidor);
		p.setModalidade(modalidade);
		p.setTimeCasa(timeCasa);
		p.setTimeVisita(timeVisita);
		return p;
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
	
	private static Jogador carregarJogador() {
		Jogador j = new Jogador();
		j.setJogador(aluno);
		j.setNumCamisa(10);
		j.setTime(timeCasa);
		j.setPosicao(posicao);
		return j;
	}
	
	private static PartidaPonto carregarPartidadePonto() {
		PartidaPonto p = new PartidaPonto();
		p.setJogador(jogador);
		p.setPartida(partida);
		p.setTipo_ponto(ponto);
		return p;
	}
	
}