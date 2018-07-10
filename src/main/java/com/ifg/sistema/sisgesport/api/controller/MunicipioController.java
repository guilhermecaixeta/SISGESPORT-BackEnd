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
import com.ifg.sistema.sisgesport.api.dto.MunicipioDTO;
import com.ifg.sistema.sisgesport.api.entities.Municipio;
import com.ifg.sistema.sisgesport.api.extesion.Extension;
import com.ifg.sistema.sisgesport.api.response.Response;
import com.ifg.sistema.sisgesport.api.services.MunicipioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/sisgesport/municipio")
public class MunicipioController extends baseController<MunicipioDTO, Municipio, MunicipioService> {
	{
		mappingDTOToEntity = new Extension<>(MunicipioDTO.class, Municipio.class);
		mappingEntityToDTO = new Extension<>(Municipio.class, MunicipioDTO.class);
	}

	@GetMapping(value = "/BuscarPorIdEstado/{id_estado}")
	public ResponseEntity<Response<List<MunicipioDTO>>> BuscarPorIdEstado(@PathVariable("id_estado") Long id_estado) {
		Optional<List<Municipio>> lista = entityService.BuscarPorEstadoId(id_estado);
		if (lista.isPresent()) {
			List<MunicipioDTO> listaDTO = mappingEntityToDTO.AsGenericMappingList(lista.get(), false);
			responseList.setData(listaDTO);
		} else
			responseList.getErrors().add("Municípios com o id do estado " + id_estado + " não encontrado.");

		return ResponseEntity.ok(responseList);
	}

	@GetMapping(value = "/BuscarPorNome/{nome}")
	public ResponseEntity<Response<MunicipioDTO>> BuscarPorNome(@PathVariable("nome") String nome) {
		entityOptional = entityService.BuscarPorNome(nome);
		if (entityOptional.isPresent()) {
			entityDTO = mappingEntityToDTO.AsGenericMapping(entityOptional.get());
			response.setData(entityDTO);
		} else
			response.getErrors().add("Instituição com o nome" + nome + " não encontrado.");

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/BuscarPorId/{id}")
	public ResponseEntity<Response<MunicipioDTO>> BuscarPorId(@PathVariable("id") Long id) {
		log.info("Buscando municipio com o id: {}", id);
		Optional<Municipio> municipio = entityService.BuscarPorId(id);
		if (!municipio.isPresent()) {
			log.info("Instituição com o id: {}, não cadastrado.", id);
			response.getErrors().add("Instituição não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(mappingEntityToDTO.AsGenericMapping(municipio.get()));
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<MunicipioDTO>> cadastrarMunicipio(@Valid @RequestBody MunicipioDTO municipioDTO,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando a municipio: {}", municipioDTO.toString());
		if (result.hasErrors()) {
			log.error("Erro ao validar dados da nova Posicao: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		entity = mappingDTOToEntity.AsGenericMapping(municipioDTO);
		entity = this.entityService.Salvar(entity);
		response.setData(mappingEntityToDTO.AsGenericMapping(entity));
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<MunicipioDTO>> atualizarMunicipio(@PathVariable("id") Long id,
			@Valid @RequestBody MunicipioDTO municipioDTO, BindingResult result) throws Exception {
		log.info("Atualizando dados do municipio: {}", municipioDTO);
		entityOptional = this.entityService.BuscarPorId(id);
		if (!entityOptional.isPresent()) {
			return ResponseEntity.badRequest().body(response);
		} else {
			entity = mappingDTOToEntity.updateGeneric(municipioDTO, entityOptional.get(), new ArrayList<String>());
		}
		entity = this.entityService.Salvar(entity);
		response.setData(mappingEntityToDTO.AsGenericMapping(entity));
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> deletarMunicipio(@PathVariable("id") Long id) {
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
