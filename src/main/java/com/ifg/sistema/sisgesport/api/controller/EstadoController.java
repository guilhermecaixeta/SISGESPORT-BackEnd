package com.ifg.sistema.sisgesport.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifg.sistema.sisgesport.api.controller.base.baseController;
import com.ifg.sistema.sisgesport.api.dto.EstadoDTO;
import com.ifg.sistema.sisgesport.api.entities.Estado;
import com.ifg.sistema.sisgesport.api.extesion.Extension;
import com.ifg.sistema.sisgesport.api.response.Response;
import com.ifg.sistema.sisgesport.api.services.EstadoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/estado")
public class EstadoController extends baseController<EstadoDTO, Estado, EstadoService> {
	{
		mappingDTOToEntity = new Extension<>(EstadoDTO.class, Estado.class);
		mappingEntityToDTO = new Extension<>(Estado.class, EstadoDTO.class);
	}

	@GetMapping(value = "/BuscarTodos")
	public ResponseEntity<Response<List<EstadoDTO>>> BuscarTodos() {
		Optional<List<Estado>> estadoLista = entityService.BuscarTodos();
		if (estadoLista.isPresent()) {
			List<EstadoDTO> estadoDTOLista = mappingEntityToDTO.AsGenericMappingList(estadoLista.get(), false);
			responseList.setData(estadoDTOLista);
		} else
			response.getErrors().add("Nenhum estado encontrado.");

		return ResponseEntity.ok(responseList);
	}
	
	@GetMapping(value = "/BuscarPorIdEstado/{id}")
	public ResponseEntity<Response<EstadoDTO>> BuscarPorIdEstado(@PathVariable("id") Long id) {
		Optional<Estado> estado = entityService.BuscarPorId(id);
		if (estado.isPresent()) {
			EstadoDTO estadoDTO = mappingEntityToDTO.AsGenericMapping(estado.get());
			response.setData(estadoDTO);
		} else
			response.getErrors().add("Estado com o id " + id + " não encontrado.");

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/BuscarPorNomeOuUF/{nome_uf}")
	public ResponseEntity<Response<EstadoDTO>> BuscarPorNomeOuUF(@PathVariable("nome_uf") String nome_uf) {
		entityOptional = entityService.BuscarPorNomeOuUF(nome_uf, nome_uf);
		if (entityOptional.isPresent()) {
			entityDTO = mappingEntityToDTO.AsGenericMapping(entityOptional.get());
			response.setData(entityDTO);
		} else
			response.getErrors().add("Instituição com o nome" + nome_uf + " não encontrado.");

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/BuscarPorId/{id}")
	public ResponseEntity<Response<EstadoDTO>> BuscarPorId(@PathVariable("id") Long id) {
		log.info("Buscando estado com o id: {}", id);
		Optional<Estado> estado = entityService.BuscarPorId(id);
		if (!estado.isPresent()) {
			log.info("Instituição com o id: {}, não cadastrado.", id);
			response.getErrors().add("Instituição não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(mappingEntityToDTO.AsGenericMapping(estado.get()));
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<EstadoDTO>> cadastrarEstado(@Valid @RequestBody EstadoDTO estadoDTO,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando a estado: {}", estadoDTO.toString());
		if (result.hasErrors()) {
			log.error("Erro ao validar dados da nova Posicao: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		entity = mappingDTOToEntity.AsGenericMapping(estadoDTO);
		entity = this.entityService.Salvar(entity);
		response.setData(mappingEntityToDTO.AsGenericMapping(entity));
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<EstadoDTO>> atualizarEstado(@PathVariable("id") Long id,
			@Valid @RequestBody EstadoDTO estadoDTO, BindingResult result) throws Exception {
		log.info("Atualizando dados do estado: {}", estadoDTO);
		entityOptional = this.entityService.BuscarPorId(id);
		if (!entityOptional.isPresent()) {
			return ResponseEntity.badRequest().body(response);
		} else {
			entity = mappingDTOToEntity.updateGeneric(estadoDTO, entityOptional.get(), new ArrayList<String>());
		}
		entity = this.entityService.Salvar(entity);
		response.setData(mappingEntityToDTO.AsGenericMapping(entity));
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> deletarEstado(@PathVariable("id") Long id) {
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
