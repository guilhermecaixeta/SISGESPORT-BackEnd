package com.ifg.sistema.sisgesport.api.repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import com.ifg.sistema.sisgesport.api.entities.Servidor;
import com.ifg.sistema.sisgesport.api.enums.PerfilSistema;
import com.ifg.sistema.sisgesport.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class EquipeRepositorioTeste {

	@Autowired
	private EquipeRepositorio equipeRepositorio;
	@Autowired
	private EventoRepositorio eventoRepositorio;
	@Autowired
	private ServidorRepositorio servidorRepositorio;
	@Autowired
	private CargoRepositorio cargoRepositorio;
	
	private static final Evento evento = CarregaEvento();
	private static final Cargo cargo = cargoServidor();
	private static final Servidor servidor = carregaServidor();
	private Equipe equipe;
	
	@Before
	public void setUp() throws Exception{
		cargoRepositorio.save(cargo);
		servidorRepositorio.save(servidor);
		eventoRepositorio.save(evento);
		Equipe eqp = new Equipe();
		eqp.setCor("AEIOU");
		eqp.setEvento(evento);
		eqp.setNome("Equipe um");
		equipeRepositorio.save(eqp);
		equipe = eqp;
	}
	
	@After
	public final void tearDown() {
		equipeRepositorio.deleteAll();
	}
	
	@Test
	public void testBuscarporCodigo() {
		Equipe eqp = equipeRepositorio.findByCodigoEquipe(equipe.getCodigoEquipe());
		
		assertNotNull(eqp);
	}
	
	@Test
	public void testBuscarporEventoId() {
		List<Equipe> eqp = equipeRepositorio.findByEventoId(evento.getId());
		
		assertNotNull(eqp);
	}
	
	@Test
	public void testBuscarporEventoIdPaginavel() {
		PageRequest page = new PageRequest(0, 10);
		Page<Equipe> listaEquipe = equipeRepositorio.findByEventoId(evento.getId(), page);
		
		assertEquals(1, listaEquipe.getTotalElements());
	}
	
	private static Servidor carregaServidor() {
		Servidor serv = new Servidor();
		serv.setNome("Guilherme Caixeta");
		serv.setDataNascimento(new Date());
		serv.setSenha(PasswordUtils.GerarBCrypt("usuario"));
		serv.setSexo('M');
		serv.setMatricula("20122080010125");
		serv.setCargo(cargo);
		serv.setPerfil(PerfilSistema.ROLE_ADMIN);
		return serv;
	}
	
	private static Cargo cargoServidor() {
		Cargo c = new Cargo();
		c.setDescricao("Lecionar aulas");
		c.setNome("Professor EJA");
		return c;
	}
	
	private static Evento CarregaEvento() {
		Evento ev = new Evento();
		ev.setDataFimInscricao(new Date());
		ev.setDataInicioInscricao(new Date());
		ev.setDataInicio(new Date());
		ev.setDataFim(new Date());
		ev.setDescricao("Evento teste");
		ev.setNome("Evento de Teste");
		ev.setQntEquipes(3);
		ev.setCriador(servidor);
		return ev;
	}
}
