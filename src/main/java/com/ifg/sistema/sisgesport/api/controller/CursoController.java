package com.ifg.sistema.sisgesport.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping(value = "/BuscarEquipePorIdCursoPaginavel/{id_instituicao}")
	public ResponseEntity<Response<Page<CursoDTO>>> BuscarCursoPorIdEventoPaginavel(
			@PathVariable("id_instituicao") Long id_instituicao, @RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "order", defaultValue = "id") String order,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "sort", defaultValue = "DESC") String sort) {
		PageRequest pageRequest = new PageRequest(page, size, Direction.valueOf(sort), order);
		Page<CursoDTO> pageServidorDTO = mappingEntityToDTO
				.AsGenericMappingListPage(entityService.BuscarEquipePorIdInstituicaoPaginavel(id_instituicao, pageRequest));
		responsePage.setData(pageServidorDTO);
		return ResponseEntity.ok(responsePage);
	}
	
	@GetMapping(value = "/BuscarEquipePorIdInstituicao/{id_instituicao}")
	public ResponseEntity<Response<List<CursoDTO>>> BuscarEquipePorIdEvento(
			@PathVariable("id_instituicao") Long id_instituicao) {
		List<CursoDTO> listServidorDTO = mappingEntityToDTO
				.AsGenericMappingList(entityService.BuscarEquipePorIdInstituicao(id_instituicao).get(), false);
		responseList.setData(listServidorDTO);
		return ResponseEntity.ok(responseList);
	}
	
	@GetMapping(value = "/buscarPorId/{id}")
	public ResponseEntity<Response<CursoDTO>> buscarPorId(@PathVariable("id") Long id) {
		log.info("Buscando Instituição com o id: {}", id);
		Optional<Curso> curso = entityService.BuscarPorId(id);
		if (!curso.isPresent()) {
			log.info("Instituição com o id: {}, não cadastrado.", id);
			response.getErrors().add("Instituição não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(mappingEntityToDTO.AsGenericMapping(curso.get()));
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
		entity = this.entityService.Salvar(entity);
		response.setData(mappingEntityToDTO.AsGenericMapping(entity));
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<CursoDTO>> AtualizarCurso(@PathVariable("id") Long id,
			@Valid @RequestBody CursoDTO cursoDTO, BindingResult result) throws Exception {
		log.info("Atualizando dados do Instituto: {}", cursoDTO);
		Optional<Curso> curso = this.entityService.BuscarPorId(id);
		List<String> lista = new ArrayList<String>();
		if (!curso.isPresent()) {
			result.addError(new ObjectError("instituicao", "Curso não encontrada para o id: " + id));
			return ResponseEntity.badRequest().body(response);
		} else {
			entity = mappingDTOToEntity.updateGeneric(cursoDTO, curso.get(), lista);
			entity = this.entityService.Salvar(entity);
			response.setData(mappingEntityToDTO.AsGenericMapping(entity));
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
