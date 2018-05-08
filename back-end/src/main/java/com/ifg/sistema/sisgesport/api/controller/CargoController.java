package com.ifg.sistema.sisgesport.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
@RequestMapping("api/sisgesport/cargo")
public class CargoController extends baseController<CargoDTO, Cargo, CargoService>{
	{
		mappingDTOToEntity = new Extension<>(CargoDTO.class, Cargo.class);
		mappingEntityToDTO = new Extension<>(Cargo.class, CargoDTO.class);
	}
	@GetMapping(value = "/buscarPorId/{id}")
	public ResponseEntity<Response<CargoDTO>> buscarPorId(@PathVariable("id") Long id) {
		log.info("Buscando Cargo com o id: {}", id);
		Optional<Cargo> cargo = entityService.BuscarPorId(id);
		if (!cargo.isPresent()) {
			log.info("Cargo com o id: {}, não cadastrado.", id);
			response.getErrors().add("Cargo não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(mappingEntityToDTO.AsGenericMapping(cargo.get()));
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<CargoDTO>> cadastrarCargo(@Valid @RequestBody CargoDTO cargoDTO,
			BindingResult result) throws NoSuchAlgorithmException {

		log.info("Cadastrando a Cargo: {}", cargoDTO.toString());
		
		if (result.hasErrors()) {
			log.error("Erro ao validar dados da nova Cargo: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		Cargo cargo = mappingDTOToEntity.AsGenericMapping(cargoDTO);
		this.entityService.Salvar(cargo);
		response.setData(mappingEntityToDTO.AsGenericMapping(cargo));
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<CargoDTO>> AtualizarCargo(@PathVariable("id") Long id,
			@Valid @RequestBody CargoDTO cargoDTO, BindingResult result) throws Exception {
		log.info("Atualizando dados do Cargo: {}", cargoDTO);
		Optional<Cargo> cargo = this.entityService.BuscarPorId(id);
		List<String> lista = new ArrayList<String>();
		if (!cargo.isPresent()) {
			result.addError(new ObjectError("Cargo", "Cargo não encontrada para o id: " + id));
			return ResponseEntity.badRequest().body(response);
		} else {
			lista.add("id");
			Cargo cargoEdit = mappingDTOToEntity.updateGeneric(cargoDTO, cargo.get(), lista);

			this.entityService.Salvar(cargoEdit);
			response.setData(mappingEntityToDTO.AsGenericMapping(cargoEdit));
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
