package com.ifg.sistema.sisgesport.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.ifg.sistema.sisgesport.api.controller.base.baseController;
import com.ifg.sistema.sisgesport.api.dto.PosicaoDTO;
import com.ifg.sistema.sisgesport.api.entities.Posicao;
import com.ifg.sistema.sisgesport.api.extesion.Extension;
import com.ifg.sistema.sisgesport.api.response.Response;
import com.ifg.sistema.sisgesport.api.services.PosicaoService;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/posicao")
public class PosicaoController extends baseController<PosicaoDTO, Posicao, PosicaoService> {
	{
		mappingDTOToEntity = new Extension<>(PosicaoDTO.class, Posicao.class);
		mappingEntityToDTO = new Extension<>(Posicao.class, PosicaoDTO.class);
	}

	@GetMapping(value = "/BuscarTodos/{id_modalidade}")
	public ResponseEntity<Response<Page<PosicaoDTO>>> BuscarTodos(
			@PathVariable("id_modalidade") Long id_modalidade,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "order", defaultValue = "id") String order,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "sort", defaultValue = "DESC") String sort) {
		PageRequest pageRequest = new PageRequest(page, size, Direction.valueOf(sort), order);
		Page<PosicaoDTO> pageServidorDTO = mappingEntityToDTO
				.AsGenericMappingListPage(entityService.BuscarPorModalidadeIdPaginavel(id_modalidade, pageRequest));
		responsePage.setData(pageServidorDTO);
		return ResponseEntity.ok(responsePage);
	}

	@GetMapping(value = "/BuscarPorNome/{nome}")
	public ResponseEntity<Response<PosicaoDTO>> BuscarPorNome(@PathVariable("nome") String nome) {
		entityOptional = entityService.BuscarPorNome(nome);
		if (entityOptional.isPresent()) {
			entityDTO = mappingEntityToDTO.AsGenericMapping(entityOptional.get());
			response.setData(entityDTO);
		} else
			response.getErrors().add("Posicao com o nome" + nome + " não encontrado.");

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/BuscarPorId/{id}")
	public ResponseEntity<Response<PosicaoDTO>> BuscarPorId(@PathVariable("id") Long id) {
		log.info("Buscando Posicao com o id: {}", id);
		Optional<Posicao> posicao = entityService.BuscarPorId(id);
		if (!posicao.isPresent()) {
			log.info("Instituição com o id: {}, não cadastrado.", id);
			response.getErrors().add("Instituição não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(mappingEntityToDTO.AsGenericMapping(posicao.get()));
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<PosicaoDTO>> cadastrarPosicao(@Valid @RequestBody PosicaoDTO posicaoDTO,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando a posicao: {}", posicaoDTO.toString());
		if (result.hasErrors()) {
			log.error("Erro ao validar dados da nova posicao: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		entity = this.entityService.Salvar(mappingDTOToEntity.AsGenericMapping(posicaoDTO));
		response.setData(mappingEntityToDTO.AsGenericMapping(entity));
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<PosicaoDTO>> atualizarPosicao(@PathVariable("id") Long id,
			@Valid @RequestBody PosicaoDTO posicaoDTO, BindingResult result) throws Exception {
		log.info("Atualizando dados da posicao: {}", posicaoDTO);
		entityOptional = this.entityService.BuscarPorId(id);
		if (!entityOptional.isPresent()) {
			return ResponseEntity.badRequest().body(response);
		} else {
			entity = mappingDTOToEntity.updateGeneric(posicaoDTO, entityOptional.get(), new ArrayList<String>());
		}
		entity = this.entityService.Salvar(entity);
		response.setData(mappingEntityToDTO.AsGenericMapping(entity));
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> deletarPosicao(@PathVariable("id") Long id) {
		Response<String> response = new Response<String>();
		if (!this.entityService.BuscarPorId(id).isPresent()) {
			log.info("Erro ao remover dados ligados ao id: {}", id);
			response.getErrors().add("Erro ao remover dado. Nenhum registro encontrado para o id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		this.entityService.Deletar(id);
		return ResponseEntity.ok(new Response<String>());
	}
}
