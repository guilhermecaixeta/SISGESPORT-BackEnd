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
import org.springframework.web.bind.annotation.*;

import com.ifg.sistema.sisgesport.api.controller.base.baseController;
import com.ifg.sistema.sisgesport.api.dto.TipoPontoDTO;
import com.ifg.sistema.sisgesport.api.entities.TipoPonto;
import com.ifg.sistema.sisgesport.api.extesion.Extension;
import com.ifg.sistema.sisgesport.api.response.Response;
import com.ifg.sistema.sisgesport.api.services.TipoPontoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/tipoPonto")
public class TipoPontoController extends baseController<TipoPontoDTO, TipoPonto, TipoPontoService> {
	{
		mappingDTOToEntity = new Extension<>(TipoPontoDTO.class, TipoPonto.class);
		mappingEntityToDTO = new Extension<>(TipoPonto.class, TipoPontoDTO.class);
	}

	@GetMapping(value = "/BuscarPorModalidadeIdPaginavel/{id_modalidade}")
	public ResponseEntity<Response<Page<TipoPontoDTO>>> BuscarPorModalidadeIdPaginavel(
			@PathVariable("id_modalidade") Long id_modalidade, @RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "order", defaultValue = "id") String order,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "sort", defaultValue = "DESC") String sort) {
		PageRequest pageRequest = new PageRequest(page, size, Direction.valueOf(sort), order);
		Page<TipoPontoDTO> pageServidorDTO = mappingEntityToDTO
				.AsGenericMappingListPage(entityService.BuscarPorModalidadeIdPaginavel(id_modalidade, pageRequest));
		responsePage.setData(pageServidorDTO);
		return ResponseEntity.ok(responsePage);
	}

	@GetMapping(value = "/BuscarPorModalidadeId/{id_modalidade}")
	public ResponseEntity<Response<List<TipoPontoDTO>>> BuscarPorModalidadeId(@PathVariable("id_modalidade") Long id_modalidade) {
		List<TipoPontoDTO> listServidorDTO = mappingEntityToDTO
				.AsGenericMappingList(entityService.BuscarPorModalidadeId(id_modalidade).get(), false);
		responseList.setData(listServidorDTO);
		return ResponseEntity.ok(responseList);
	}

	@GetMapping(value = "/BuscarPorId/{id}")
	public ResponseEntity<Response<TipoPontoDTO>> BuscarPorId(@PathVariable("id") Long id) {
		log.info("Buscando Instituição com o id: {}", id);
		Optional<TipoPonto> tipoPonto = entityService.BuscarPorId(id);
		if (!tipoPonto.isPresent()) {
			log.info("Instituição com o id: {}, não cadastrado.", id);
			response.getErrors().add("Instituição não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(mappingEntityToDTO.AsGenericMapping(tipoPonto.get()));
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<TipoPontoDTO>> cadastrarTipoPonto(@Valid @RequestBody TipoPontoDTO tipoPontoDTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando a tipoPonto: {}", tipoPontoDTO.toString());
		if (result.hasErrors()) {
			log.error("Erro ao validar dados da nova tipoPonto: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		entity = mappingDTOToEntity.AsGenericMapping(tipoPontoDTO);
		entity = this.entityService.Salvar(entity);
		response.setData(mappingEntityToDTO.AsGenericMapping(entity));
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<TipoPontoDTO>> atualizarTipoPonto(@PathVariable("id") Long id,
			@Valid @RequestBody TipoPontoDTO tipoPontoDTO, BindingResult result) throws Exception {
		log.info("Atualizando dados do Instituto: {}", tipoPontoDTO);
		Optional<TipoPonto> entityDTO = this.entityService.BuscarPorId(id);
		if (!entityDTO.isPresent()) {
			return ResponseEntity.badRequest().body(response);
		} else {
			entity = mappingDTOToEntity.updateGeneric(tipoPontoDTO, entityDTO.get(), new ArrayList<String>());
		}
		entity = this.entityService.Salvar(entity);
		response.setData(mappingEntityToDTO.AsGenericMapping(entity));
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> deletarTipoPonto(@PathVariable("id") Long id) {
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
