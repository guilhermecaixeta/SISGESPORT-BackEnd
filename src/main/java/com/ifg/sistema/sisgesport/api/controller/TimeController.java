package com.ifg.sistema.sisgesport.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.ifg.sistema.sisgesport.api.entities.Jogador;
import com.ifg.sistema.sisgesport.api.entities.PageConfiguration;
import com.ifg.sistema.sisgesport.api.services.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.ifg.sistema.sisgesport.api.controller.base.baseController;
import com.ifg.sistema.sisgesport.api.dto.TimeDTO;
import com.ifg.sistema.sisgesport.api.entities.Time;
import com.ifg.sistema.sisgesport.api.extesion.Extension;
import com.ifg.sistema.sisgesport.api.response.Response;
import com.ifg.sistema.sisgesport.api.services.TimeService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/time")
public class TimeController extends baseController<TimeDTO, Time, TimeService> {
	{
        listaExcecao.add("id");
        listaExcecao.add("equipe");
        listaExcecao.add("modalidade");
        listaExcecao.add("serialVersionUID");
		mappingDTOToEntity = new Extension<>(TimeDTO.class, Time.class);
		mappingEntityToDTO = new Extension<>(Time.class, TimeDTO.class);
	}

	@Autowired
	private JogadorService jogadorService;

	@GetMapping(value = "/BuscarPorEquipeIdPaginavel/{id_time}")
	public ResponseEntity<Response<Page<TimeDTO>>> BuscarTimePorIdEventoPaginavel(
			@PathVariable("id_time") Long id_time, PageConfiguration pageConfig) {
		PageRequest pageRequest = new PageRequest(pageConfig.page, pageConfig.size, Direction.valueOf(pageConfig.sort),
				pageConfig.order);
		entityPageListDTO = mappingEntityToDTO
				.AsGenericMappingListPage(entityService.BuscarPorEquipeIdPaginavel(id_time, pageRequest));
		responsePage.setData(entityPageListDTO);
		return ResponseEntity.ok(responsePage);
	}

	@GetMapping(value = "/BuscarTimePorIdEvento/{id_time}")
	public ResponseEntity<Response<List<TimeDTO>>> BuscarTimePorIdEvento(@PathVariable("id_time") Long id_time) {
		entityListDTO = mappingEntityToDTO
				.AsGenericMappingList(entityService.BuscarPorEquipeId(id_time).get(), false);
		responseList.setData(entityListDTO);
		return ResponseEntity.ok(responseList);
	}

	@GetMapping(value = "/BuscarPorEventoIdEModalidadeId/{id_evento}/{id_modalidade}")
	public ResponseEntity<Response<List<TimeDTO>>> BuscarPorEventoIdEModalidadeId(
			@PathVariable("id_evento") Long id_evento, @PathVariable("id_modalidade") Long id_modalidade) {
		entityListDTO = mappingEntityToDTO
				.AsGenericMappingList(entityService.BuscarPorEventoIdEModalidadeId(id_evento, id_modalidade).get(),
						false);
		responseList.setData(entityListDTO);
		return ResponseEntity.ok(responseList);
	}

	@GetMapping(value = "/BuscarPorId/{id}")
	public ResponseEntity<Response<TimeDTO>> BuscarPorId(@PathVariable("id") Long id) {
		log.info("Buscando Instituição com o id: {}", id);
		Optional<Time> time = entityService.BuscarPorId(id);
		if (!time.isPresent()) {
			log.info("Instituição com o id: {}, não cadastrado.", id);
			response.getErrors().add("Instituição não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(mappingEntityToDTO.AsGenericMapping(time.get()));
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<TimeDTO>> cadastrarTime(@Valid @RequestBody TimeDTO timeDTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando a time: {}", timeDTO.toString());
		if (result.hasErrors()) {
			log.error("Erro ao validar dados da nova time: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		entity = mappingDTOToEntity.AsGenericMapping(timeDTO);
		entity = this.entityService.Salvar(entity);
        response.setData(mappingEntityToDTO.AsGenericMapping(entity));
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<TimeDTO>> atualizarTime(@PathVariable("id") Long id,
			@Valid @RequestBody TimeDTO timeDTO, BindingResult result) throws Exception {
		log.info("Atualizando dados do Instituto: {}", timeDTO);
		Optional<Time> time = this.entityService.BuscarPorId(id);
		if (!time.isPresent()) {
			return ResponseEntity.badRequest().body(response);
		} else {
			entity = mappingDTOToEntity.updateGeneric(timeDTO, time.get(), listaExcecao);
		}
        List<Jogador> listaJogador = new ArrayList<>();
        listaJogador = entity.getJogador();
        entity.setJogador(null);
        entity = this.entityService.Salvar(entity);
        listaJogador.forEach(x -> {
            x.setTime(entity);
            jogadorService.Salvar(x);
        });
		response.setData(mappingEntityToDTO.AsGenericMapping(entity));
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> deletarTime(@PathVariable("id") Long id) {
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
