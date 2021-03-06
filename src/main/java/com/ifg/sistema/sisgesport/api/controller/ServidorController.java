package com.ifg.sistema.sisgesport.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.ifg.sistema.sisgesport.api.entities.PageConfiguration;
import com.ifg.sistema.sisgesport.api.enums.PerfilSistema;
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
import com.ifg.sistema.sisgesport.api.dto.ServidorDTO;
import com.ifg.sistema.sisgesport.api.dto.EnderecoDTO;
import com.ifg.sistema.sisgesport.api.entities.Servidor;
import com.ifg.sistema.sisgesport.api.entities.Endereco;
import com.ifg.sistema.sisgesport.api.entities.Cargo;
import com.ifg.sistema.sisgesport.api.extesion.Extension;
import com.ifg.sistema.sisgesport.api.response.Response;
import com.ifg.sistema.sisgesport.api.services.ServidorService;
import com.ifg.sistema.sisgesport.api.services.EnderecoService;
import com.ifg.sistema.sisgesport.api.services.CargoService;
import com.ifg.sistema.sisgesport.api.utils.PasswordUtils;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/servidor")
public class ServidorController  extends baseController<ServidorDTO, Servidor, ServidorService> {
	{
        listaExcecao.add("serialVersionUID");
        listaExcecao.add("senha");
        listaExcecao.add("turma");
        listaExcecao.add("id");
		mappingDTOToEntity = new Extension<>(ServidorDTO.class, Servidor.class);
		mappingEntityToDTO = new Extension<>(Servidor.class, ServidorDTO.class);
	}
	protected Extension<EnderecoDTO, Endereco> mappingEntityChild = new Extension<>(EnderecoDTO.class, Endereco.class);

    @Autowired
    protected EnderecoService enderecoService;

	@GetMapping(value = "/BuscarPorCargoInstituicaoIdPaginavel/{id_instituicao}")
	public ResponseEntity<Response<Page<ServidorDTO>>> BuscarPorCargoInstituicaoIdPaginavel(@PathVariable("id_instituicao") Long id_instituicao,
		PageConfiguration pageConfig) {

		PageRequest pageRequest = new PageRequest(pageConfig.page, pageConfig.size, Direction.valueOf(pageConfig.sort), pageConfig.order);
		entityPageListDTO = mappingEntityToDTO
				.AsGenericMappingListPage(entityService.BuscarPorCargoInstituicaoIdPaginavel(id_instituicao, pageRequest));
		entityPageListDTO.forEach(data -> data.setSenha(null));
		responsePage.setData(entityPageListDTO);
		return ResponseEntity.ok(responsePage);
	}

	@GetMapping(value = "/BuscarPorCargoIdPaginavel/{idCargo}")
	public ResponseEntity<Response<Page<ServidorDTO>>> BuscarPorCargoIdPaginavel(@PathVariable("idCargo") Long idCargo,
			PageConfiguration pageConfig) {
        response = new Response<ServidorDTO>();
		PageRequest pageRequest = new PageRequest(pageConfig.page, pageConfig.size, Direction.valueOf(pageConfig.sort), pageConfig.order);
		entityPageListDTO = mappingEntityToDTO
				.AsGenericMappingListPage(entityService.BuscarPorCargoIdPaginavel(idCargo, pageRequest));
		entityPageListDTO.forEach(data -> data.setSenha(null));
		responsePage.setData(entityPageListDTO);
		return ResponseEntity.ok(responsePage);
	}

	@GetMapping(value = "/BuscarTodos")
	public ResponseEntity<Response<List<ServidorDTO>>> BuscarTodos() {
		responseList = new Response<List<ServidorDTO>>();
		entityListDTO = mappingEntityToDTO
				.AsGenericMappingList(entityService.BuscarTodos().get(), false);
		entityListDTO.forEach(data -> data.setSenha(null));
		responseList.setData(entityListDTO);
		return ResponseEntity.ok(responseList);
	}

	@GetMapping(value = "/BuscarPorMatricula/{matriculaSiap}")
	public ResponseEntity<Response<ServidorDTO>> BuscarPorMatriculaSiap(@PathVariable("matriculaSiap") String matriculaSiap) {
		log.info("Buscando servidor com a matrícula: {}", matriculaSiap);
		response = new Response<ServidorDTO>();
		entityOptional = entityService.BuscarPorMatriculaSiap(matriculaSiap);
		if (!entityOptional.isPresent()) {
			log.info("Servidor com a matrícula: {}, não cadastrado.", matriculaSiap);
			response.getErrors().add("Servidor não encontrado para a matrícula " + matriculaSiap);
			return ResponseEntity.badRequest().body(response);
		} else {
			entityDTO = mappingEntityToDTO.AsGenericMapping(entityOptional.get());
			response.setData(entityDTO);
			return ResponseEntity.ok(response);
		}
	}

	@PostMapping()
	public ResponseEntity<Response<ServidorDTO>> CadastrarServidor(@Valid @RequestBody ServidorDTO servidorDTO,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando o servidor: {}", servidorDTO.toString());
		validarServidor(servidorDTO, result);
		if (result.hasErrors()) {
			log.error("Erro ao validar dados do novo servidor: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		entity = mappingDTOToEntity.AsGenericMapping(servidorDTO);
		entity.setPerfil(PerfilSistema.ROLE_USUARIO);
		List<Endereco> listaEndereco = entity.getEndereco();
        entity.setEndereco(new ArrayList<Endereco>());
		if(!listaEndereco.isEmpty())
            listaEndereco.forEach(endereco -> entity.AdicionarEndereco(endereco));
		response.setData(mappingEntityToDTO.AsGenericMapping(this.entityService.Salvar(entity)));
		return ResponseEntity.ok(response);
	}

	private void validarServidor(ServidorDTO servidorDTO, BindingResult result) {
		this.entityService.BuscarPorMatriculaSiap(servidorDTO.getMatricula())
					.ifPresent(alu -> result.addError(new ObjectError("servidor", "Matrícula já cadastrada.")));

		this.entityService.BuscarPorEmail(servidorDTO.getEmail())
				.ifPresent(alu -> result.addError(new ObjectError("servidor", "Email já cadastrado.")));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<ServidorDTO>> atualizarServidor(@PathVariable("id") Long id,
			@Valid @RequestBody ServidorDTO servidorDTO, BindingResult result) throws Exception {
		log.info("Atualizando dados do Servidor: {}", servidorDTO);
		entityOptional = entityService.BuscarPorId(id);
		if (!entityOptional.isPresent())
			result.addError(new ObjectError("Servidor", "Servidor não encontrado"));
		
		if (servidorDTO.getEmail() != null && !servidorDTO.getEmail().equals(entityOptional.get().getEmail())) {
			entityService.BuscarPorEmail(servidorDTO.getEmail())
					.ifPresent(al -> result.addError(new ObjectError("email", "Email já cadastrado")));
		}
		if (entityOptional.isPresent()) {
			entity = mappingDTOToEntity.updateGeneric(servidorDTO, entityOptional.get(), listaExcecao);
            List<Endereco> listaEnderecos = entity.getEndereco();
            entity.setEndereco(new ArrayList<Endereco>());
            if (!servidorDTO.getEndereco().isEmpty()) {
                entityOptional.get().getEndereco().forEach(endereco -> enderecoService.Deletar(endereco.getId()));
                listaEnderecos.forEach(endereco -> entity.AdicionarEndereco(endereco));
            }
			this.entityService.Salvar(entity);
			response.setData(mappingEntityToDTO.AsGenericMapping(entity));
		} else {
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> deletarServidor(@PathVariable("id") Long id) {
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
