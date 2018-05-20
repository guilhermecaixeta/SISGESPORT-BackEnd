package com.ifg.sistema.sisgesport.api.repositorios;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

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
import com.ifg.sistema.sisgesport.api.entities.Evento;
import com.ifg.sistema.sisgesport.api.entities.Servidor;
import com.ifg.sistema.sisgesport.api.enums.PerfilSistema;
import com.ifg.sistema.sisgesport.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class EventoRepositorioTeste {
	
	@Autowired
	private EventoRepositorio evR;
	@Autowired
	private ServidorRepositorio sR;
	@Autowired
	private CargoRepositorio cR;
	
	private static final Cargo cargo = cargoServidor();
	private static final Servidor servidor = carregaServidor();

	@Before
	public void setUp() throws Exception{
		cR.save(cargo);
		sR.save(servidor);
		Evento ev = new Evento();
		ev.setDataFim(new Date());
		ev.setDataInicio(new Date());
		ev.setDataFimInscricao(new Date());
		ev.setDataInicioInscricao(new Date());
		ev.setDescricao("Evento teste 01");
		ev.setNome("Evento de Teste 01");
		ev.setQntEquipes(3);
		ev.setCriador(servidor);
		evR.save(ev);
	}
	
	@Test
	public void testBuscarporCriadorPaginavel() {
		PageRequest page = new PageRequest(0, 10);
		Page<Evento> evento = evR.findByCriadorMatricula(servidor.getMatricula(), page);
		assertNotNull(evento);
	}

	@Test
	public void testBuscarporCriador() {
		List<Evento> evento = evR.findByCriadorMatricula(servidor.getMatricula());
		assertNotNull(evento);
	}
	
	private static Servidor carregaServidor() {
		Servidor serv = new Servidor();
		serv.setNome("Guilherme");
		serv.setDataNascimento(new Date());
		serv.setSenha(PasswordUtils.GerarBCrypt("usuario"));
		serv.setSexo('M');
		serv.setMatricula("20122080010547");
		serv.setCargo(cargo);
		serv.setPerfil(PerfilSistema.ROLE_ADMIN);
		return serv;
	}
	
	private static Cargo cargoServidor() {
		Cargo c = new Cargo();
		c.setDescricao("Lecionar aulas");
		c.setNome("Educador de Ensino Superior");
		return c;
	}
}
