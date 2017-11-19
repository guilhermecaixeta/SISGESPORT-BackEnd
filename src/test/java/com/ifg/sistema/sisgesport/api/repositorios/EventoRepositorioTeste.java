package com.ifg.sistema.sisgesport.api.repositorios;

import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

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
import com.ifg.sistema.sisgesport.api.entities.Servidor;
import com.ifg.sistema.sisgesport.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class EventoRepositorioTeste {
	
	@Autowired
	private EventoRepositorio evR;
	@Autowired
	private ServidorRepositorio servidorRepositorio;
	@Autowired
	private CargoRepositorio cR;
	
	private Evento ev;
	private static final Cargo cargo = cargoServidor();
	private static final Servidor servidor = carregaServidor();

	@Before
	public void setUp() throws Exception{
		cR.save(cargo);
		servidorRepositorio.save(servidor);
		Evento ev = new Evento();
		ev.setData_fim(Calendar.getInstance());
		ev.setData_inicio(Calendar.getInstance());
		ev.setData_fim_inscricao(Calendar.getInstance());
		ev.setData_inicio_inscricao(Calendar.getInstance());
		ev.setDescricao("Evento teste");
		ev.setNome("Evento de Teste");
		ev.setQnt_equipes(3);
		ev.setCriador(servidor);
		evR.save(ev);
		this.ev = ev;
	}
	
	@After
	public final void tearDown() {
		evR.deleteAll();
	}
	
	@Test
	public void testBuscarporId() {
		Evento evento = evR.findById(ev.getId());
		assertNotNull(evento);
	}

	@Test
	public void testBuscarporCriador() {
		Evento evento = evR.findByCriadorMatriculasiap(servidor.getMatricula_siap());
		assertNotNull(evento);
	}
	
	private static Servidor carregaServidor() {
		Servidor serv = new Servidor();
		serv.setNome("Guilherme");
		serv.setData_nasc(Calendar.getInstance());
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
		c.setNome("Professor Superior");
		return c;
	}
}
