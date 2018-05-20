package com.ifg.sistema.sisgesport.api.repositorios;

import static org.junit.Assert.assertNotNull;

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

import com.ifg.sistema.sisgesport.api.entities.Cargo;
import com.ifg.sistema.sisgesport.api.entities.Evento;
import com.ifg.sistema.sisgesport.api.entities.Modalidade;
import com.ifg.sistema.sisgesport.api.entities.Servidor;
import com.ifg.sistema.sisgesport.api.entities.EventoModalidade;
import com.ifg.sistema.sisgesport.api.enums.PerfilSistema;
import com.ifg.sistema.sisgesport.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class EventoModalidadeRepositorioTeste {
	@Autowired
	private EventoRepositorio evR;
	@Autowired
	private ServidorRepositorio sR;
	@Autowired
	private CargoRepositorio cR;
	@Autowired
	private ModalidadeRepositorio mR;
	@Autowired
	private EventoModalidadeRepositorio emR;
	
	private static final Cargo cargo = cargoServidor();
	private static final Servidor servidor = carregaServidor();
	private static final Evento evento = carregarEvento();
	private static final Modalidade modal = carregarModalidade();
	@Before
	public void setUp() throws Exception{
		cR.save(cargo);
		sR.save(servidor);
		evR.save(evento);
		mR.save(modal);
		EventoModalidade em = new EventoModalidade();
		em.setEvento(evento);
		em.setIdadeMaximaPermitida(15);
		em.setModalidade(modal);
		em.setSexo('A');
	}
	@After
	public final void tearDown() {
		evR.deleteAll();
	}
	
	@Test
	public void testBuscarPorEventoId() {
		List<EventoModalidade> em = emR.findByEventoId(evento.getId());
		assertNotNull(em);
	}
	
	@Test
	public void testBuscarPorModalidadeId() {
		List<EventoModalidade> em = emR.findByEventoId(modal.getId());
		assertNotNull(em);
	}
	
	private static Servidor carregaServidor() {
		Servidor serv = new Servidor();
		serv.setNome("Guilherme");
		serv.setDataNascimento(new Date());
		serv.setSenha(PasswordUtils.GerarBCrypt("usuario"));
		serv.setSexo('M');
		serv.setMatricula("20122080010177");
		serv.setCargo(cargo);
		serv.setPerfil(PerfilSistema.ROLE_ADMIN);
		return serv;
	}
	
	private static Cargo cargoServidor() {
		Cargo c = new Cargo();
		c.setDescricao("Lecionar aulas");
		c.setNome("Professor E. S.");
		return c;
	}
	
	private static Evento carregarEvento() {
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
	private static Modalidade carregarModalidade() {
		Modalidade mod = new Modalidade();
		mod.setDescricao("Esporte Coletivo de até 6 jogadores.");
		mod.setNome("Vôlei");
		mod.setNumMaxJogador(21);
		mod.setNumMinJogador(11);
		return mod;
	}
}
