package com.ifg.sistema.sisgesport.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.ifg.sistema.sisgesport.api.dto.PenalidadeDTO;
import com.ifg.sistema.sisgesport.api.dto.PosicaoDTO;
import com.ifg.sistema.sisgesport.api.dto.TipoPontoDTO;
import com.ifg.sistema.sisgesport.api.entities.Penalidade;
import com.ifg.sistema.sisgesport.api.entities.Posicao;
import com.ifg.sistema.sisgesport.api.entities.TipoPonto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ifg.sistema.sisgesport.api.controller.base.baseController;
import com.ifg.sistema.sisgesport.api.dto.ModalidadeDTO;
import com.ifg.sistema.sisgesport.api.entities.Modalidade;
import com.ifg.sistema.sisgesport.api.extesion.Extension;
import com.ifg.sistema.sisgesport.api.response.Response;
import com.ifg.sistema.sisgesport.api.services.ModalidadeService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/modalidade")
public class ModalidadeController extends baseController<ModalidadeDTO, Modalidade, ModalidadeService> {
	{
		mappingDTOToEntity = new Extension<>(ModalidadeDTO.class, Modalidade.class);
		mappingEntityToDTO = new Extension<>(Modalidade.class, ModalidadeDTO.class);
	}
    protected Extension<PosicaoDTO, Posicao> mappingEntityChildI = new Extension<>(PosicaoDTO.class, Posicao.class);
    protected Extension<TipoPontoDTO, TipoPonto> mappingEntityChildII = new Extension<>(TipoPontoDTO.class, TipoPonto.class);
    protected Extension<PenalidadeDTO, Penalidade> mappingEntityChildIII = new Extension<>(PenalidadeDTO.class, Penalidade.class);

	@GetMapping(value = "/BuscarTodosPaginavel")
	public ResponseEntity<Response<Page<ModalidadeDTO>>> BuscarTodosPaginavel(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "order", defaultValue = "id") String order,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "sort", defaultValue = "DESC") String sort) {
		PageRequest pageRequest = new PageRequest(page, size, Direction.valueOf(sort), order);
		Page<ModalidadeDTO> pageServidorDTO = mappingEntityToDTO
				.AsGenericMappingListPage(entityService.BuscarTodosPaginavel(pageRequest));
		responsePage.setData(pageServidorDTO);
		return ResponseEntity.ok(responsePage);
	}

	@GetMapping(value = "/BuscarPorNome/{nome}")
	public ResponseEntity<Response<ModalidadeDTO>> BuscarPorNome(@PathVariable("nome") String nome) {
		entityOptional = entityService.BuscarPorNome(nome);
		if (entityOptional.isPresent()) {
			entityDTO = mappingEntityToDTO.AsGenericMapping(entityOptional.get());
			response.setData(entityDTO);
		} else
			response.getErrors().add("Instituição com o nome" + nome + " não encontrado.");

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/BuscarPorId/{id}")
	public ResponseEntity<Response<ModalidadeDTO>> BuscarPorId(@PathVariable("id") Long id) {
		log.info("Buscando modalidade com o id: {}", id);
		Optional<Modalidade> modalidade = entityService.BuscarPorId(id);
		if (!modalidade.isPresent()) {
			log.info("Instituição com o id: {}, não cadastrado.", id);
			response.getErrors().add("Instituição não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(mappingEntityToDTO.AsGenericMapping(modalidade.get()));
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<ModalidadeDTO>> cadastrarModalidade(@Valid @RequestBody ModalidadeDTO modalidadeDTO,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando a modalidade: {}", modalidadeDTO.toString());
		if (result.hasErrors()) {
			log.error("Erro ao validar dados da nova Posicao: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		entity = mappingDTOToEntity.AsGenericMapping(modalidadeDTO);
		entity = this.entityService.Salvar(entity);
		response.setData(mappingEntityToDTO.AsGenericMapping(entity));
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<ModalidadeDTO>> atualizarModalidade(@PathVariable("id") Long id,
			@Valid @RequestBody ModalidadeDTO modalidadeDTO, BindingResult result) throws Exception {
		log.info("Atualizando dados do modalidade: {}", modalidadeDTO);
		entityOptional = this.entityService.BuscarPorId(id);
        List<String> lista = new ArrayList<String>();

		if (!entityOptional.isPresent()) {
			return ResponseEntity.badRequest().body(response);
		} else {
            List<Penalidade> listaI = new ArrayList<Penalidade>();
            List<TipoPonto> listaII = new ArrayList<TipoPonto>();
            List<Posicao> listaIII = new ArrayList<Posicao>();
		    lista.add("id");
		    modalidadeDTO.getPenalidade().forEach(x -> listaI.add(mappingEntityChildIII.AsGenericMapping(x)));
            modalidadeDTO.getTipoPonto().forEach(x -> listaII.add(mappingEntityChildII.AsGenericMapping(x)));
            modalidadeDTO.getPosicao().forEach(x -> listaIII.add(mappingEntityChildI.AsGenericMapping(x)));
			entity = mappingDTOToEntity.updateGeneric(modalidadeDTO, entityOptional.get(), lista);
			entity.setPenalidade(listaI);
            entity.setTipoPonto(listaII);
            entity.setPosicao(listaIII);
		}
		entity = this.entityService.Salvar(entity);
		response.setData(mappingEntityToDTO.AsGenericMapping(entity));
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> deletarModalidade(@PathVariable("id") Long id) {
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
