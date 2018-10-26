package com.ifg.sistema.sisgesport.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.ifg.sistema.sisgesport.api.entities.PageConfiguration;
import com.ifg.sistema.sisgesport.api.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.GetMapping;

import com.ifg.sistema.sisgesport.api.controller.base.baseController;
import com.ifg.sistema.sisgesport.api.dto.EnderecoDTO;
import com.ifg.sistema.sisgesport.api.dto.InstituicaoDTO;
import com.ifg.sistema.sisgesport.api.entities.Endereco;
import com.ifg.sistema.sisgesport.api.entities.Instituicao;
import com.ifg.sistema.sisgesport.api.extesion.Extension;
import com.ifg.sistema.sisgesport.api.response.Response;
import com.ifg.sistema.sisgesport.api.services.InstituicaoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/instituicao")
public class InstitutoController extends baseController<InstituicaoDTO, Instituicao, InstituicaoService> {
	{
		mappingDTOToEntity = new Extension<>(InstituicaoDTO.class, Instituicao.class);
		mappingEntityToDTO = new Extension<>(Instituicao.class, InstituicaoDTO.class);
	}
    @Autowired
    protected EnderecoService enderecoService;

    protected Extension<EnderecoDTO, Endereco> mappingEntityChild = new Extension<>(EnderecoDTO.class, Endereco.class);

    @GetMapping(value = "/BuscarTodosPaginavel")
	public ResponseEntity<Response<Page<InstituicaoDTO>>> BuscarTodosPaginavel(PageConfiguration pageConfig)
    {
		PageRequest pageRequest = new PageRequest(pageConfig.page, pageConfig.size, Sort.Direction.valueOf(pageConfig.sort), pageConfig.order);
		Page<InstituicaoDTO> pageDTO = mappingEntityToDTO
				.AsGenericMappingListPage(entityService.BuscarTodosPaginavel(pageRequest));
		responsePage.setData(pageDTO );
		return ResponseEntity.ok(responsePage);
	}

	@GetMapping(value = "/BuscarTodos")
	public ResponseEntity<Response<List<InstituicaoDTO>>> BuscarTodos()
    {
		Optional<List<Instituicao>> estadoLista = entityService.BuscarTodos();
		if (estadoLista.isPresent()) {
			List<InstituicaoDTO> estadoDTOLista = mappingEntityToDTO.AsGenericMappingList(estadoLista.get(), false);
			responseList.setData(estadoDTOLista);
		} else
			response.getErrors().add("Nenhuma instituição encontrada.");

		return ResponseEntity.ok(responseList);
	}
	
	@GetMapping(value = "/BuscarPorNomeInstituicao/{nome}")
	public ResponseEntity<Response<InstituicaoDTO>> BuscarPorNomeInstituicao(@PathVariable("nome") String nome) {
		log.info("Buscando Instituição com o nome: {}", nome);
		Optional<Instituicao> instituto = entityService.BuscarPorNomeInstituicao(nome);
		if (!instituto.isPresent()) {
			log.info("Instituição com o nome: {}, não cadastrado.", nome);
			response.getErrors().add("Instituição não encontrado para o nome " + nome);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(mappingEntityToDTO.AsGenericMapping(instituto.get()));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/BuscarPorId/{id}")
	public ResponseEntity<Response<InstituicaoDTO>> BuscarPorId(@PathVariable("id") Long id) {
		log.info("Buscando Instituição com o id: {}", id);
		Optional<Instituicao> instituto = entityService.BuscarPorId(id);
		if (!instituto.isPresent()) {
			log.info("Instituição com o id: {}, não cadastrado.", id);
			response.getErrors().add("Instituição não encontrado para o id " + id.toString());
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(mappingEntityToDTO.AsGenericMapping(instituto.get()));
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<InstituicaoDTO>> cadastrarInstituicao(
			@Valid @RequestBody InstituicaoDTO instituicaoDTO, BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando a instituicao: {}", instituicaoDTO.toString());
		this.entityService.BuscarPorNomeInstituicao(instituicaoDTO.getNome())
				.ifPresent(inst -> result.addError(new ObjectError("instituicao", "Nome já cadastrado.")));
		if (result.hasErrors()) {
			log.error("Erro ao validar dados da nova instituicao: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		entity = mappingDTOToEntity.AsGenericMapping(instituicaoDTO);
		List<Endereco> listaEnderecos = entity.getEndereco();
		entity.setEndereco(new ArrayList<Endereco>());
		if (!listaEnderecos.isEmpty()) 
			listaEnderecos.forEach(endereco -> entity.AdicionarEndereco(endereco));
		response.setData(mappingEntityToDTO.AsGenericMapping(this.entityService.Salvar(entity)));
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<InstituicaoDTO>> atualizarInstituicao(@PathVariable("id") Long id,
			@Valid @RequestBody InstituicaoDTO institutoDTO, BindingResult result) throws Exception {
		log.info("Atualizando dados do Instituto: {}", institutoDTO);
		Optional<Instituicao> instituto = this.entityService.BuscarPorId(id);
		List<String> lista = new ArrayList<String>();
		if (!instituto.isPresent()) {
			result.addError(new ObjectError("instituicao", "Instituicao não encontrada para o id: " + id.toString()));
			return ResponseEntity.badRequest().body(response);
		} else {
			lista.add("id");
			lista.add("endereco");
			entity = mappingDTOToEntity.updateGeneric(institutoDTO, instituto.get(), lista);
			List<Endereco> listaEnderecos = mappingEntityChild.AsGenericMappingList(institutoDTO.getEndereco(), false);
			entity.setEndereco(new ArrayList<Endereco>());
			if (!institutoDTO.getEndereco().isEmpty()) {
                instituto.get().getEndereco().forEach(endereco -> enderecoService.Deletar(endereco.getId()));
                listaEnderecos.forEach(endereco -> entity.AdicionarEndereco(endereco));
            }
			response.setData(mappingEntityToDTO.AsGenericMapping(this.entityService.Salvar(entity)));
			return ResponseEntity.ok(response);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> deletarInstituicao(@PathVariable("id") Long id) {
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
