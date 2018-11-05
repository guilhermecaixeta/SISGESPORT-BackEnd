package com.ifg.sistema.sisgesport.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.ifg.sistema.sisgesport.api.entities.PageConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import com.ifg.sistema.sisgesport.api.controller.base.baseController;
import com.ifg.sistema.sisgesport.api.dto.EnderecoDTO;
import com.ifg.sistema.sisgesport.api.dto.EventoDTO;
import com.ifg.sistema.sisgesport.api.entities.Endereco;
import com.ifg.sistema.sisgesport.api.entities.Evento;
import com.ifg.sistema.sisgesport.api.extesion.Extension;
import com.ifg.sistema.sisgesport.api.response.Response;
import com.ifg.sistema.sisgesport.api.services.EnderecoService;
import com.ifg.sistema.sisgesport.api.services.EventoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/evento")
public class EventoController extends baseController<EventoDTO, Evento, EventoService> {
	{
		mappingDTOToEntity = new Extension<>(EventoDTO.class, Evento.class);
		mappingEntityToDTO = new Extension<>(Evento.class, EventoDTO.class);
	}
	@Autowired
	private EnderecoService eS;
	
	protected Extension<EnderecoDTO, Endereco> mappingEntityChild = new Extension<>(EnderecoDTO.class, Endereco.class);
	
	@GetMapping(value = "/BuscarPorMatriculaCriadorPaginavel/{matriculaSiap}")
	public ResponseEntity<Response<Page<EventoDTO>>> BuscarPorMatriculaCriadorPaginavel(@PathVariable("matriculaSiap") String matriculaSiap, PageConfiguration pageConfig) {
		PageRequest pageRequest = new PageRequest(pageConfig.page, pageConfig.size, Direction.valueOf(pageConfig.sort), pageConfig.order);
		pageEntity = mappingEntityToDTO
				.AsGenericMappingListPage(entityService.BuscarPorMatriculaCriadorPaginavel(matriculaSiap, pageRequest));
		responsePage.setData(pageEntity);
		return ResponseEntity.ok(responsePage);
	}

	@GetMapping(value = "/BuscarTodosPaginavel")
	public ResponseEntity<Response<Page<EventoDTO>>> BuscarTodosPaginavel(PageConfiguration pageConfig) {
		PageRequest pageRequest = new PageRequest(pageConfig.page, pageConfig.size, Direction.valueOf(pageConfig.sort), pageConfig.order);
		pageEntity = mappingEntityToDTO
				.AsGenericMappingListPage(entityService.BuscarTodosPaginavel(pageRequest));
		responsePage.setData(pageEntity);
		return ResponseEntity.ok(responsePage);
	}
	
	@GetMapping(value = "/BuscarPorMatriculaCriador/{matriculaSiap}")
	public ResponseEntity<Response<List<EventoDTO>>> BuscarPorMatriculaCriador(@PathVariable("matriculaSiap") String matriculaSiap) {
		entityListDTO = mappingEntityToDTO
				.AsGenericMappingList(entityService.BuscarPorMatriculaCriador(matriculaSiap).get(), false);
		responseList.setData(entityListDTO);
		return ResponseEntity.ok(responseList);
	}
	
	@GetMapping(value = "/BuscarPorId/{id}")
	public ResponseEntity<Response<EventoDTO>> BuscarPorId(@PathVariable("id") Long id) {
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

	@PostMapping
	public ResponseEntity<Response<EventoDTO>> CadastrarEvento(@Valid @RequestBody EventoDTO eventoDTO,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando a evento: {}", eventoDTO.toString());
		this.entityService.BuscarPorCodigoEvento(eventoDTO.getCodigoEvento())
				.ifPresent(inst -> result.addError(new ObjectError("evento", "Codigo evento já cadastrado.")));
		if (result.hasErrors()) {
			log.error("Erro ao validar dados da nova evento: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		entity = mappingDTOToEntity.AsGenericMapping(eventoDTO);
		response.setData(mappingEntityToDTO.AsGenericMapping(this.entityService.Salvar(entity)));
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<EventoDTO>> AtualizarEvento(@PathVariable("id") Long id, @Valid @RequestBody EventoDTO eventoDTO, BindingResult result) throws Exception {
		log.info("Atualizando dados do Instituto: {}", eventoDTO);
		entityOptional = this.entityService.BuscarPorId(id);
		List<String> lista = new ArrayList<String>();
		if (!entityOptional.isPresent()) {
			result.addError(new ObjectError("evento", "Evento não encontrada para o id: " + id));
			return ResponseEntity.badRequest().body(response);
		} else {
			lista.add("endereco");
			entityOptional.get().getEndereco().forEach(endereco -> this.eS.Deletar(endereco.getId()));
			entity = mappingDTOToEntity.updateGeneric(eventoDTO, entityOptional.get(), lista);
			if(eventoDTO.getEndereco().size() > 0) {
				List<Endereco> enderecos = mappingEntityChild.AsGenericMappingList(eventoDTO.getEndereco(), true);
				enderecos.forEach(endereco -> entity.AdicionarEndereco(endereco));
			}
			this.entityService.Salvar(entity);
			response.setData(mappingEntityToDTO.AsGenericMapping(entity));
			return ResponseEntity.ok(response);
		}
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Response<String>> deletarEvento(@PathVariable("id") Long id) {
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