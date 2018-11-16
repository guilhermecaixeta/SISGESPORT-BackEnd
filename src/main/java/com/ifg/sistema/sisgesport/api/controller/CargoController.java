package com.ifg.sistema.sisgesport.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.ifg.sistema.sisgesport.api.entities.PageConfiguration;
import com.ifg.sistema.sisgesport.api.services.InstituicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.RestController;

import com.ifg.sistema.sisgesport.api.controller.base.baseController;
import com.ifg.sistema.sisgesport.api.dto.CargoDTO;
import com.ifg.sistema.sisgesport.api.entities.Cargo;
import com.ifg.sistema.sisgesport.api.extesion.Extension;
import com.ifg.sistema.sisgesport.api.response.Response;
import com.ifg.sistema.sisgesport.api.services.CargoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/cargo")
public class CargoController extends baseController<CargoDTO, Cargo, CargoService>{
	{
		listaExcecao.add("id");
		listaExcecao.add("serialVersionUID");
		mappingDTOToEntity = new Extension<>(CargoDTO.class, Cargo.class);
		mappingEntityToDTO = new Extension<>(Cargo.class, CargoDTO.class);
	}

	@GetMapping(value = "/BuscarTodosPaginavel")
	public ResponseEntity<Response<Page<CargoDTO>>> BuscarTodosPaginavel(PageConfiguration pageConfig)
	{
		PageRequest pageRequest = new PageRequest(pageConfig.page, pageConfig.size, Sort.Direction.valueOf(pageConfig.sort), pageConfig.order);
		entityPageListDTO = mappingEntityToDTO
				.AsGenericMappingListPage(entityService.BuscarTodosPaginavel(pageRequest));
		responsePage.setData(entityPageListDTO);
		return ResponseEntity.ok(responsePage);
	}

	@GetMapping(value = "/BuscarPorId/{id}")
	public ResponseEntity<Response<CargoDTO>> BuscarPorId(@PathVariable("id") Long id) {
		log.info("Buscando Cargo com o id: {}", id);
		entityOptional = entityService.BuscarPorId(id);
		if (!entityOptional.isPresent()) {
			log.info("Cargo com o id: {}, não cadastrado.", id);
			response.getErrors().add("Cargo não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(mappingEntityToDTO.AsGenericMapping(entityOptional.get()));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/BuscarPorInstituicaoId/{id_instituicao}")
	public ResponseEntity<Response<List<CargoDTO>>> BuscarPorInstituicaoId(
			@PathVariable("id_instituicao") Long id_instituicao) {
		List<CargoDTO> listServidorDTO = mappingEntityToDTO
				.AsGenericMappingList(entityService.BuscarPorInstituicaoId(id_instituicao).get(), false);
		responseList.setData(listServidorDTO);
		return ResponseEntity.ok(responseList);
	}
	
	@PostMapping
	public ResponseEntity<Response<CargoDTO>> cadastrarCargo(@Valid @RequestBody CargoDTO cargoDTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando a Cargo: {}", cargoDTO.getNome());
		if (result.hasErrors()) {
			log.error("Erro ao validar dados da nova Cargo: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		entity = mappingDTOToEntity.AsGenericMapping(cargoDTO);
		this.entityService.Salvar(entity);
		response.setData(mappingEntityToDTO.AsGenericMapping(entity));
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<CargoDTO>> AtualizarCargo(@PathVariable("id") Long id,
            @Valid @RequestBody CargoDTO cargoDTO, BindingResult result) throws Exception {
		log.info("Atualizando dados do Cargo: {}", cargoDTO);
		entityOptional = this.entityService.BuscarPorId(id);
		if (!entityOptional.isPresent()) {
			result.addError(new ObjectError("Cargo", "Cargo não encontrado para o id: " + id));
			return ResponseEntity.badRequest().body(response);
		} else {
            entity = mappingDTOToEntity.updateGeneric(cargoDTO, entityOptional.get(), listaExcecao);
			response.setData(mappingEntityToDTO.AsGenericMapping(this.entityService.Salvar(entity)));
			return ResponseEntity.ok(response);
		}
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Response<String>> deletarCargo(@PathVariable("id") Long id) {
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
