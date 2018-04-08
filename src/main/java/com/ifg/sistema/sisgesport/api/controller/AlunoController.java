package com.ifg.sistema.sisgesport.api.controller;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifg.sistema.sisgesport.api.controller.base.baseController;
import com.ifg.sistema.sisgesport.api.dto.AlunoDTO;
import com.ifg.sistema.sisgesport.api.entities.Aluno;
import com.ifg.sistema.sisgesport.api.mapping.mappingEntitys;
import com.ifg.sistema.sisgesport.api.response.Response;
import com.ifg.sistema.sisgesport.api.services.AlunoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("sisgesport/aluno")
public class AlunoController extends baseController<AlunoDTO, Aluno> {
	{
	mappingDTOToEntity = new mappingEntitys<>(AlunoDTO.class, Aluno.class);
	mappingEntityToDTO = new mappingEntitys<>(Aluno.class, AlunoDTO.class);
	}
	private Response<AlunoDTO> response;
	@Autowired
	private AlunoService aS;

	public AlunoController() {
	}

	// @GetMapping(value="/obterTodos/{pageable}")
	// public Page<Aluno> Get(@PathVariable("pageable")Pageable pageable){
	// return aS.BuscarPorIdEquipePaginavel(id_equipee, pageable);
	// }

	@PostMapping
	public ResponseEntity<Response<AlunoDTO>> cadastrar(@Valid @RequestBody AlunoDTO alunoDTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando o aluno: {}", alunoDTO.toString());
		response = new Response<AlunoDTO>();
		validarAluno(alunoDTO, result);
		
		Aluno aluno = mappingDTOToEntity.AsGenericMapping(alunoDTO);
		if (result.hasErrors()) {
			log.error("Erro ao validar dados do novo aluno: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		this.aS.Salvar(aluno);
		response.setData(mappingEntityToDTO.AsGenericMapping(aluno));
		return ResponseEntity.ok(response);
	}
	
	private void validarAluno( AlunoDTO alunoDTO, BindingResult result) {
		this.aS.BuscarPorMatricula(alunoDTO.getMatricula()).ifPresent(alu -> result.addError(new ObjectError("aluno", "Aluno já cadastrado.")));
	}

}
