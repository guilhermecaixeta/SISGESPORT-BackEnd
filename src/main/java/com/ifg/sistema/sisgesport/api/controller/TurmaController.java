package com.ifg.sistema.sisgesport.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.ifg.sistema.sisgesport.api.entities.PageConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import com.ifg.sistema.sisgesport.api.dto.TurmaDTO;
import com.ifg.sistema.sisgesport.api.entities.Turma;
import com.ifg.sistema.sisgesport.api.extesion.Extension;
import com.ifg.sistema.sisgesport.api.response.Response;
import com.ifg.sistema.sisgesport.api.services.TurmaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/turma")
public class TurmaController  extends baseController<TurmaDTO, Turma, TurmaService>{
	{
		mappingDTOToEntity = new Extension<>(TurmaDTO.class, Turma.class);
		mappingEntityToDTO = new Extension<>(Turma.class, TurmaDTO.class);
	}
	
	@GetMapping(value = "/BuscarPorId/{id}")
	public ResponseEntity<Response<TurmaDTO>> buscarPorId(@PathVariable("id") Long id) {
		log.info("Buscando Turma com o id: {}", id);
		Optional<Turma> turma = entityService.BuscarPorId(id);
		if (!turma.isPresent()) {
			log.info("Turma com o id: {}, não cadastrado.", id);
			response.getErrors().add("Instituição não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(mappingEntityToDTO.AsGenericMapping(turma.get()));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/BuscarPorNome/{nome}")
	public ResponseEntity<Response<TurmaDTO>> buscarPorNome(@PathVariable("nome") String nome) {
		log.info("Buscando Turma com o nome: {}", nome);
		Optional<Turma> turma = entityService.BuscarPorNome(nome);
		if (!turma.isPresent()) {
			log.info("Turma com o nome: {}, não cadastrado.", nome);
			response.getErrors().add("Instituição não encontrado para o nome " + nome);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(mappingEntityToDTO.AsGenericMapping(turma.get()));
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/BuscarPorCursoId/{id_curso}")
	public ResponseEntity<Response<List<TurmaDTO>>> buscarPorCurso(@PathVariable("id_curso") Long id_curso) {
		log.info("Buscando Turma com o curso de id : {}", id_curso);
		Optional<List<Turma>> turmas = entityService.BuscarPorCursoId(id_curso);
		if (!turmas.isPresent()) {
			log.info("Turmas com o id do curso: {}, não cadastrado.", id_curso);
			responseList.getErrors().add("Turmas não encontradas para o id: " + id_curso);
			return ResponseEntity.badRequest().body(responseList);
		}
		responseList.setData(mappingEntityToDTO.AsGenericMappingList(turmas.get(), false));
		return ResponseEntity.ok(responseList);
	}

	@GetMapping(value = "/buscarTodosPorCurso/{id_curso}")
	public ResponseEntity<Response<Page<TurmaDTO>>> buscarTodosPorCursoPaginavel(@PathVariable("id_curso") Long id_curso,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "order", defaultValue = "id") String order,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "sort", defaultValue = "DESC") String sort) {
		PageRequest pageRequest = new PageRequest(page, size, Direction.valueOf(sort), order);
		Page<TurmaDTO> pageTurmaDTO = mappingEntityToDTO
				.AsGenericMappingListPage(entityService.BuscarPorCursoIdPaginavel(id_curso,pageRequest));
		responsePage.setData(pageTurmaDTO);
		return ResponseEntity.ok(responsePage);
	}

	@GetMapping(value = "/BuscarTodosPaginavel")
	public ResponseEntity<Response<Page<TurmaDTO>>> BuscarTodosPaginavel(PageConfiguration pageConfig)
	{
		PageRequest pageRequest = new PageRequest(pageConfig.page, pageConfig.size, Sort.Direction.valueOf(pageConfig.sort), pageConfig.order);
		Page<TurmaDTO> pageDTO = mappingEntityToDTO
				.AsGenericMappingListPage(entityService.BuscarTodosPaginavel(pageRequest));
		responsePage.setData(pageDTO );
		return ResponseEntity.ok(responsePage);
	}
	
	@PostMapping
	public ResponseEntity<Response<TurmaDTO>> cadastrarTurma(@Valid @RequestBody TurmaDTO turmaDTO,
			BindingResult result) throws NoSuchAlgorithmException {

		log.info("Cadastrando a instituicao: {}", turmaDTO.toString());
		
		if (result.hasErrors()) {
			log.error("Erro ao validar dados da nova instituicao: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		Turma turma = mappingDTOToEntity.AsGenericMapping(turmaDTO);
		this.entityService.Salvar(turma);
		response.setData(mappingEntityToDTO.AsGenericMapping(turma));
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<TurmaDTO>> atualizarTurma(@PathVariable("id") Long id,
			@Valid @RequestBody TurmaDTO turmaDTO, BindingResult result) throws Exception {
		log.info("Atualizando dados do Instituto: {}", turmaDTO);
		Optional<Turma> turma = this.entityService.BuscarPorId(id);
		List<String> lista = new ArrayList<String>();
		if (!turma.isPresent()) {
			result.addError(new ObjectError("instituicao", "Instituicao não encontrada para o id: " + id));
			return ResponseEntity.badRequest().body(response);
		} else {
			
			Turma turmaEdit = mappingDTOToEntity.updateGeneric(turmaDTO, turma.get(), lista);

			this.entityService.Salvar(turmaEdit);
			response.setData(mappingEntityToDTO.AsGenericMapping(turmaEdit));
			return ResponseEntity.ok(response);
		}
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Response<String>> deletarTurma(@PathVariable("id") Long id) {
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
