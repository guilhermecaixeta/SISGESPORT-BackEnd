package com.ifg.sistema.sisgesport.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.ifg.sistema.sisgesport.api.entities.PageConfiguration;
import com.ifg.sistema.sisgesport.api.entities.Time;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ifg.sistema.sisgesport.api.dto.EquipeDTO;
import com.ifg.sistema.sisgesport.api.entities.Equipe;
import com.ifg.sistema.sisgesport.api.extesion.Extension;
import com.ifg.sistema.sisgesport.api.response.Response;
import com.ifg.sistema.sisgesport.api.services.EquipeService;
import com.ifg.sistema.sisgesport.api.services.ImagemService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/equipe")
public class EquipeController extends baseController<EquipeDTO, Equipe, EquipeService> {
	{
        lista.add("id");
        lista.add("time");
        lista.add("evento");
        lista.add("serialVersionUID");
		mappingDTOToEntity = new Extension<>(EquipeDTO.class, Equipe.class);
		mappingEntityToDTO = new Extension<>(Equipe.class, EquipeDTO.class);
	}
	@Autowired
	private ImagemService iS;

	@GetMapping(value = "/BuscarEquipePorIdEventoPaginavel/{id_evento}")
	public ResponseEntity<Response<Page<EquipeDTO>>> BuscarEquipePorIdEventoPaginavel(
			@PathVariable("id_evento") Long id_evento,
			PageConfiguration pageConfig) {
		PageRequest pageRequest = new PageRequest(pageConfig.page, pageConfig.size, Direction.valueOf(pageConfig.sort), pageConfig.order);
		pageEntity = mappingEntityToDTO
				.AsGenericMappingListPage(entityService.BuscarEquipePorIdEventoPaginavel(id_evento, pageRequest));
		pageEntity.forEach(x ->{
		    x.setTime(null);
		    x.setEvento(null);
        });
		responsePage.setData(pageEntity);
		return ResponseEntity.ok(responsePage);
	}

	@GetMapping(value = "/BuscarPorMatriculaCriador/{matriculaSiap}")
	public ResponseEntity<Response<List<EquipeDTO>>> BuscarEquipePorIdEvento(
			@PathVariable("id_evento") Long id_evento) {
		List<EquipeDTO> listServidorDTO = mappingEntityToDTO
				.AsGenericMappingList(entityService.BuscarEquipePorIdEvento(id_evento).get(), false);
		responseList.setData(listServidorDTO);
		return ResponseEntity.ok(responseList);
	}

	@GetMapping(value = "/BuscarPorId/{id}")
	public ResponseEntity<Response<EquipeDTO>> BuscarPorId(@PathVariable("id") Long id) {
		log.info("Buscando equipe com o id: {}", id);
		entityOptional = entityService.BuscarPorId(id);
		if (!entityOptional.isPresent()) {
			log.info("Equipe com o id: {}, não cadastrado.", id);
			response.getErrors().add("Equipe não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
        entityOptional.get().getAluno();
		entityDTO = mappingEntityToDTO.AsGenericMapping(entityOptional.get());
		entityDTO.getEvento().getCriador().setSenha(null);
		response.setData(entityDTO);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<EquipeDTO>> cadastrarEquipe(@Valid @RequestBody EquipeDTO equipeDTO,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando a equipe: {}", equipeDTO.toString());
		this.entityService.BuscarPorCodigoEquipe(equipeDTO.getCodigoEquipe())
				.ifPresent(inst -> result.addError(new ObjectError("equipe", "Codigo equipe já cadastrado.")));
		if (result.hasErrors()) {
			log.error("Erro ao validar dados da nova equipe: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		entity = mappingDTOToEntity.AsGenericMapping(equipeDTO);
		this.entityService.Salvar(entity);
		response.setData(new EquipeDTO());
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<EquipeDTO>> atualizarEquipe(@PathVariable("id") Long id,
			@Valid @RequestBody EquipeDTO equipeDTO, BindingResult result) throws Exception {
		log.info("Atualizando dados da equipe: {}", equipeDTO.getNome());
		entityOptional = this.entityService.BuscarPorId(id);
		if (!entityOptional.isPresent()) {
			result.addError(new ObjectError("equipe", "Equipe não encontrada para o id: " + id));
			return ResponseEntity.badRequest().body(response);
		} else {
			entity = mappingDTOToEntity.updateGeneric(equipeDTO, entityOptional.get(), lista);

			this.entityService.Salvar(entity);
			response.setData(mappingEntityToDTO.AsGenericMapping(entity));
		}
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> deletarEquipe(@PathVariable("id") Long id) {
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
