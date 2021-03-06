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
		listaExcecao.add("id");
		listaExcecao.add("serialVersionUID");
		mappingDTOToEntity = new Extension<>(TipoPontoDTO.class, TipoPonto.class);
		mappingEntityToDTO = new Extension<>(TipoPonto.class, TipoPontoDTO.class);
	}

	@GetMapping(value = "/BuscarPorModalidadeIdPaginavel/{id_modalidade}")
	public ResponseEntity<Response<Page<TipoPontoDTO>>> BuscarPorModalidadeIdPaginavel(
			@PathVariable("id_modalidade") Long id_modalidade, PageConfiguration pageConfig) {
		PageRequest pageRequest = new PageRequest(pageConfig.page, pageConfig.size, Direction.valueOf(pageConfig.sort), pageConfig.order);
		entityPageListDTO = mappingEntityToDTO
				.AsGenericMappingListPage(entityService.BuscarPorModalidadeIdPaginavel(id_modalidade, pageRequest));
		responsePage.setData(entityPageListDTO);
		return ResponseEntity.ok(responsePage);
	}

	@GetMapping(value = "/BuscarTodosPaginavel")
	public ResponseEntity<Response<Page<TipoPontoDTO>>> BuscarTodosPaginavel( PageConfiguration pageConfig) {
		PageRequest pageRequest = new PageRequest(pageConfig.page, pageConfig.size, Direction.valueOf(pageConfig.sort), pageConfig.order);
		entityPageListDTO = mappingEntityToDTO
				.AsGenericMappingListPage(entityService.BuscarTodosPaginavel(pageRequest));
		responsePage.setData(entityPageListDTO);
		return ResponseEntity.ok(responsePage);
	}

	@GetMapping(value = "/BuscarPorModalidadeId/{id_modalidade}")
	public ResponseEntity<Response<List<TipoPontoDTO>>> BuscarPorModalidadeId(@PathVariable("id_modalidade") Long id_modalidade) {
		entityListDTO = mappingEntityToDTO
				.AsGenericMappingList(entityService.BuscarPorModalidadeId(id_modalidade).get(), false);
		responseList.setData(entityListDTO);
		return ResponseEntity.ok(responseList);
	}

	@GetMapping(value = "/BuscarPorId/{id}")
	public ResponseEntity<Response<TipoPontoDTO>> BuscarPorId(@PathVariable("id") Long id) {
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

	@GetMapping(value = "/BuscarTodos")
	public ResponseEntity<Response<List<TipoPontoDTO>>> BuscarTodos()
	{
		Optional<List<TipoPonto>> tipoPontoLista = entityService.BuscarTodos();
		if (tipoPontoLista.isPresent()) {
			entityListDTO = mappingEntityToDTO.AsGenericMappingList(tipoPontoLista.get(), false);
			responseList.setData(entityListDTO);
		} else
			response.getErrors().add("Nenhum tipo ponto encontrado.");
		return ResponseEntity.ok(responseList);
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
		entityOptional = this.entityService.BuscarPorId(id);
		if (!entityOptional.isPresent()) {
			return ResponseEntity.badRequest().body(response);
		} else {
			entity = mappingDTOToEntity.updateGeneric(tipoPontoDTO, entityOptional.get(), listaExcecao);
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
