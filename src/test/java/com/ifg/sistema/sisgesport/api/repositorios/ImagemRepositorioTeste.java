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
import com.ifg.sistema.sisgesport.api.entities.Informacao_Evento;
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
	private static final Informacao_Evento informacao_evento = carregarInformacaoEvento();
	@Before
	public void setUp() throws Exception{
		cR.save(cargo);
		srR.save(servidor);
		evR.save(evento);
		Imagem i = new Imagem();
		try {
		i.setNome("amor.jpeg");
		i.setTamanho(100.20);
		i.setDescricaoImagem("Lorem ipsulun");
		Path path = Paths.get("G:/Imagens/imagens e videos de humor/amor.jpeg");
		i.setImagem(Files.readAllBytes(path));

		}catch(Exception e) {
			e.printStackTrace();
		}
		imR.save(i);
		List<Imagem> listaI= new ArrayList<Imagem>();
		listaI.add(i);
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
	
	private static Cargo cargoInformacao() {
		Cargo c = new Cargo();
		c.setDescricao("Lecionar aulas");
		c.setNome("Professor Superior");
		return c;
	}
	
	private static Servidor servidorInformacao() {
		Servidor serv = new Servidor();
		serv.setNome("Guilherme");
		serv.setData_nasc(new Date());
		serv.setLogin("usuario");
		serv.setSenha(PasswordUtils.GerarBCrypt("usuario"));
		serv.setSexo('M');
		serv.setMatricula_siap("20122080010047");
		serv.setCargo(cargo);
		serv.setPerfil(PerfilSistema.ROLE_ADMIN);
		return serv;
	}
	
	private static Evento EventoInformacao() {
		Evento ev = new Evento();
		ev.setData_fim(new Date());
		ev.setData_inicio(new Date());
		ev.setData_fim_inscricao(new Date());
		ev.setData_inicio_inscricao(new Date());
		ev.setDescricao("Evento teste");
		ev.setNome("Evento de Teste");
		ev.setQnt_equipes(3);
		ev.setCriador(servidor);
		return ev;
	}
	
	private static Informacao_Evento carregarInformacaoEvento() {
		Informacao_Evento iE= new Informacao_Evento();
		iE.setData_postagem(new Date());
		iE.setTitulo("Lorem ipsum dolor sit amet");
		iE.setDescricao("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
		iE.setTipo_informacao('e');
		iE.setEvento(evento);
		return iE;
	}
}
