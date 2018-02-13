package com.ifg.sistema.sisgesport.api.repositorios;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

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
	private TimeRepositorioTeste tmR;
	@Autowired
	private EquipeRepositorio eR;
}
