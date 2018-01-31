package com.ifg.sistema.sisgesport.api.repositorios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ifg.sistema.sisgesport.api.entities.*;
import com.ifg.sistema.sisgesport.api.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("teste")
public class ServidorRepositorioTeste {

	@Autowired
	private ServidorRepositorio servidorRepositorio;
	@Autowired
	private CargoRepositorio cR;
	
	private static final String matriculasiap ="20122080010047";
	private static final Cargo cargo = cargoServidor();
	@Before
	public void setUp() throws Exception{
		Servidor serv = new Servidor();
		cR.save(cargo);
		serv.setCargo(cargo);
		serv.setNome("Guilherme");
		serv.setData_nasc(Calendar.getInstance());
		serv.setLogin("usuario");
		serv.setSenha(PasswordUtils.GerarBCrypt("usuario"));
		serv.setSexo('M');
		serv.setMatricula_siap(matriculasiap);
		serv.setAdmin_sistema(true);
		this.servidorRepositorio.save(serv);
		
		Servidor serv2 = new Servidor();
		serv2.setNome("user");
		serv2.setData_nasc(Calendar.getInstance());
		serv2.setLogin("user");
		serv2.setSenha(PasswordUtils.GerarBCrypt("2012208001004220122080010042"));
		serv2.setSexo('F');
		serv2.setMatricula_siap("20122080010042");
		serv2.setCargo(cargo);
		serv2.setAdmin_sistema(false);
		this.servidorRepositorio.save(serv2);
	}
	
	@After
	public final void tearDown() {
		this.servidorRepositorio.deleteAll();
	}
	
	@Test
	public void testBuscarPorMatriculaSiap() {
		Servidor serv = this.servidorRepositorio.findByMatriculasiap(matriculasiap);
		
		assertEquals(matriculasiap, serv.getMatricula_siap());
	}
	
	@Test
	public void testBuscarPorCargo() {
		List<Servidor> serv = this.servidorRepositorio.findByCargoId(cargo.getId());
		List<Servidor> compare = new ArrayList<>();
		compare.add( this.servidorRepositorio.findByMatriculasiap(matriculasiap));
		compare.add( this.servidorRepositorio.findByMatriculasiap("20122080010042"));
		int num1 =compare.size();
		int num2 = serv.size();
		
		assertEquals(num1, num2);
		assertNotNull(serv);
	}
	
	private static Cargo cargoServidor() {
		Cargo c = new Cargo();
		c.setDescricao("Lecionar aulas de física.");
		c.setNome("Professor De Física");
		return c;
	}
}
