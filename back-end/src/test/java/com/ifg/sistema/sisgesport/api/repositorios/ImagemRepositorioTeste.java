package com.ifg.sistema.sisgesport.api.repositorios;

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
public class ImagemRepositorioTeste {
	@Autowired
	private EventoRepositorio evR;
	@Autowired
	private ServidorRepositorio srR;
	@Autowired
	private CargoRepositorio cR;
	@Autowired
	private ImagemRepositorio imR;
	@Autowired
	private InformacaoEventoRepositorio iEvR;
	
	private static final Cargo cargo = cargoInformacao();
	private static final Servidor servidor = servidorInformacao();
	private static final Evento evento = EventoInformacao();
	private static final InformacaoEvento informacao_evento = carregarInformacaoEvento();
	@Before
	public void setUp() throws Exception{
		Imagem i = new Imagem();
		try {
		i.setNome("amor.jpeg");
		i.setTamanho(100.20);
		i.setDescricaoImagem("Lorem ipsulun");
		Path path = Paths.get("/home/guilherme/Imagens/ducreux.jpg");
		i.setImagem(Files.readAllBytes(path));

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Imagem i2 = new Imagem();
		try {
		i2.setNome("amor.jpeg");
		i2.setTamanho(100.20);
		i2.setDescricaoImagem("Lorem ipsulun");
		Path path = Paths.get("/home/guilherme/Imagens/ducreux.jpg");
		i2.setImagem(Files.readAllBytes(path));

		}catch(Exception e) {
			e.printStackTrace();
		}
		imR.save(i2);
		cR.save(cargo);
		servidor.AdicionarImagem(i);
		srR.save(servidor);
		evR.save(evento);
		List<Imagem> listaI= new ArrayList<Imagem>();
		listaI.add(i2);
		informacao_evento.setImagem(listaI);
		iEvR.save(informacao_evento);
	}
	
	@After
	public final void tearDown() {
		imR.deleteAll();
	}

	@Test
	public void testBuscarPorInformacaoEventoId() {
		List<Imagem> lista = this.imR.findByInformacaoEventoId(informacao_evento.getId());
		
		assertNotNull(lista);
	}
	
	@Test
	public void testBuscarPorInformacaoEntidadeComumId() {
		List<Imagem> lista = this.imR.findByEntidadeComumId(servidor.getId());
		
		assertNotNull(lista);
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
	
	private static InformacaoEvento carregarInformacaoEvento() {
		InformacaoEvento iE= new InformacaoEvento();
		iE.setDataPostagem(new Date());
		iE.setTitulo("Lorem ipsum dolor sit amet");
		iE.setDescricao("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
		iE.setTipoInformacao('e');
		iE.setEvento(evento);
		return iE;
	}
}
