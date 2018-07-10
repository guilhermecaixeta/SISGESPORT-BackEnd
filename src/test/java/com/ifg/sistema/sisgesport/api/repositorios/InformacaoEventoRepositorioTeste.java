package com.ifg.sistema.sisgesport.api.repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import com.ifg.sistema.sisgesport.api.entities.Evento;
import com.ifg.sistema.sisgesport.api.entities.Imagem;
import com.ifg.sistema.sisgesport.api.entities.InformacaoEvento;
import com.ifg.sistema.sisgesport.api.entities.Servidor;
import com.ifg.sistema.sisgesport.api.enums.PerfilSistema;
import com.ifg.sistema.sisgesport.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class InformacaoEventoRepositorioTeste {
	@Autowired
	private EventoRepositorio evR;
	@Autowired
	private ServidorRepositorio srR;
	@Autowired
	private CargoRepositorio cR;
	@Autowired
	private InformacaoEventoRepositorio iEvR;

	private static final Cargo cargo = cargoInformacao();
	private static final Servidor servidor = servidorInformacao();
	private static final Evento evento = EventoInformacao();
	private static final Imagem imagem = ImagemInformacao();

	@Before
	public void setUp() throws Exception {
		cR.save(cargo);
		srR.save(servidor);
		evR.save(evento);
		List<Imagem> listaI = new ArrayList<Imagem>();
		imagem.setId(null);
		listaI.add(imagem);
		InformacaoEvento iE = new InformacaoEvento();
		iE.setId(null);
		iE.setDataPostagem(new Date());
		iE.setTitulo("Lorem ipsum dolor sit amet");
		iE.setDescricao(
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
				+ " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
				+ " Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
				+ "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
		iE.setTipoInformacao('e');
		iE.setEvento(evento);
		iE.setImagem(listaI);
		iEvR.save(iE);
	}

	@After
	public final void tearDown() {
		iEvR.deleteAll();
	}

	@Test
	public void testBuscarPorEvento() {
		List<InformacaoEvento> lista = this.iEvR.findByEventoCodigoEvento(evento.getCodigoEvento());

		assertNotNull(lista);
	}

	@Test
	public void testBuscarPorEventoPaginado() {
		PageRequest page = new PageRequest(0, 10);
		Page<InformacaoEvento> lista = this.iEvR.findByEventoCodigoEvento(evento.getCodigoEvento(), page);

		assertEquals(1, lista.getNumberOfElements());
	}

	private static Cargo cargoInformacao() {
		Cargo c = new Cargo();
		c.setDescricao("Lecionar aulas");
		c.setNome("Professor Superior");
		return c;
	}

	private static Servidor servidorInformacao() {
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

	private static Evento EventoInformacao() {
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

	private static Imagem ImagemInformacao() {
		Imagem i = new Imagem();
		try {
			i.setNome("s2.jpeg");
			i.setTamanho(100.20);
			i.setDescricaoImagem("Lorem ipsulun");
			Path path = Paths.get("D:/Imagens/HuehueBR/futuro.jpg");
			i.setImagem(Files.readAllBytes(path));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
}
