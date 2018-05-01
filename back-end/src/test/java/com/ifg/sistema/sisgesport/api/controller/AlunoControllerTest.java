package com.ifg.sistema.sisgesport.api.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ifg.sistema.sisgesport.api.entities.Aluno;
import com.ifg.sistema.sisgesport.api.services.AlunoService;
import com.ifg.sistema.sisgesport.api.services.TurmaService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("teste")
public class AlunoControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private AlunoService aS;
	@MockBean
	private TurmaService tS;
	
	private static final String url_Busca_Aluno = "/api/sisgesport/aluno/buscarPorMatricula/";
	private static final Long id = Long.valueOf(1);
	private static final String matricula = "123456";
	private static final String nome = "Aluno Teste";
	
	@Test
	@WithMockUser
	public void TesteBuscarAlunoPorMatriculaInvalido() throws Exception {
		BDDMockito.given(aS.BuscarPorMatricula(Mockito.anyString())).willReturn(Optional.empty());
		
		mvc.perform(MockMvcRequestBuilders.get(url_Busca_Aluno + matricula).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errors").value("Aluno não encontrado para a matrícula "+ matricula));
	}
	
	@Test
	@WithMockUser
	public void TesteBuscarAlunoPorMatriculaValido() throws Exception {
		BDDMockito.given(aS.BuscarPorMatricula(Mockito.anyString())).willReturn(Optional.of(obterAluno()));
		mvc.perform(MockMvcRequestBuilders.get(url_Busca_Aluno + matricula).accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data.id").value(id))
		.andExpect(jsonPath("$.data.nome").value(nome))
		.andExpect(jsonPath("$.errors").isEmpty());
	}
	
	public Aluno obterAluno() {
		Aluno aluno = new Aluno();
		aluno.setDataNascimento(new Date());
		aluno.setEmail("teste@teste");
		aluno.setMatricula(matricula);
		aluno.setNome(nome);
		aluno.setSenha("123456");
		aluno.setSexo('F');
		aluno.setId(id);
		return aluno;
	}
}
