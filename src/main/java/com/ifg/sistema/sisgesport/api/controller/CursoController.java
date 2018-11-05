package com.ifg.sistema.sisgesport.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.ifg.sistema.sisgesport.api.entities.PageConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.ifg.sistema.sisgesport.api.controller.base.baseController;
import com.ifg.sistema.sisgesport.api.dto.CursoDTO;
import com.ifg.sistema.sisgesport.api.entities.Curso;
import com.ifg.sistema.sisgesport.api.extesion.Extension;
import com.ifg.sistema.sisgesport.api.response.Response;
import com.ifg.sistema.sisgesport.api.services.CursoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/curso")
public class CursoController extends baseController<CursoDTO, Curso, CursoService>{
	{
		mappingDTOToEntity = new Extension<>(CursoDTO.class, Curso.class);
		mappingEntityToDTO = new Extension<>(Curso.class, CursoDTO.class);
	}
	
	@GetMapping(value = "/BuscarCursoPorIdInstituicaoPaginavel/{id_instituicao}")
	public ResponseEntity<Response<Page<CursoDTO>>> BuscarCursoPorIdInstituicaoPaginavel(
			@PathVariable("id_instituicao") Long id_instituicao, PageConfiguration pageConfig) {
		PageRequest pageRequest = new PageRequest(pageConfig.page, pageConfig.size, Direction.valueOf(pageConfig.sort), pageConfig.order);
		pageEntity = mappingEntityToDTO
				.AsGenericMappingListPage(entityService.BuscarCursoPorIdInstituicaoPaginavel(id_instituicao, pageRequest));
		responsePage.setData(pageEntity);
		return ResponseEntity.ok(responsePage);
	}
	
	@GetMapping(value = "/BuscarCursoPorIdInstituicao/{id_instituicao}")
	public ResponseEntity<Response<List<CursoDTO>>> BuscarCursoPorIdInstituicao(
			@PathVariable("id_instituicao") Long id_instituicao) {
		entityListDTO = mappingEntityToDTO
				.AsGenericMappingList(entityService.BuscarCursoPorIdInstituicao(id_instituicao).get(), false);
		responseList.setData(entityListDTO);
		return ResponseEntity.ok(responseList);
	}
	
	@GetMapping(value = "/BuscarPorId/{id}")
	public ResponseEntity<Response<CursoDTO>> BuscarPorId(@PathVariable("id") Long id) {
		log.info("Buscando Instituição com o id: {}", id);
		entityOptional = entityService.BuscarPorId(id);
		if (!entityOptional.isPresent()) {
			log.info("Instituição com o id: {}, não cadastrado.", id);
			response.getErrors().add("Instituição não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(mappingEntityToDTO.AsGenericMapping(entityOptional.get()));
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<CursoDTO>> cadastrarCurso(@Valid @RequestBody CursoDTO cursoDTO,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando a instituicao: {}", cursoDTO.toString());
		if (result.hasErrors()) {
			log.error("Erro ao validar dados da nova instituicao: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		entity = mappingDTOToEntity.AsGenericMapping(cursoDTO);
		response.setData(mappingEntityToDTO.AsGenericMapping(this.entityService.Salvar(entity)));
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<CursoDTO>> AtualizarCurso(@PathVariable("id") Long id,
			@Valid @RequestBody CursoDTO cursoDTO, BindingResult result) throws Exception {
		log.info("Atualizando dados do Instituto: {}", cursoDTO);
		entityOptional = this.entityService.BuscarPorId(id);
		List<String> lista = new ArrayList<String>();
		if (!entityOptional.isPresent()) {
			result.addError(new ObjectError("instituicao", "Curso não encontrada para o id: " + id));
			return ResponseEntity.badRequest().body(response);
		} else {
			entity = mappingDTOToEntity.updateGeneric(cursoDTO, entityOptional.get(), lista);
			response.setData(mappingEntityToDTO.AsGenericMapping(this.entityService.Salvar(entity)));
			return ResponseEntity.ok(response);
		}
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Response<String>> deletarCurso(@PathVariable("id") Long id) {
		Response<String> response = new Response<String>();
		if(!this.entityService.BuscarPorId(id).isPresent()) {
			log.info("Erro ao remover dados ligados ao id: {}", id);
			response.getErrors().add("Erro ao remover dado. Nenhum registro encontrado para o id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		this.entityService.Deletar(id);
		return ResponseEntity.ok(new Response<String>());
	}
}
