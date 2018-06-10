package com.ifg.sistema.sisgesport.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
@RequestMapping("api/sisgesport/servidor")
public class ServidorController  extends baseController<ServidorDTO, Servidor, ServidorService> {
	{
		mappingDTOToEntity = new Extension<>(ServidorDTO.class, Servidor.class);
		mappingEntityToDTO = new Extension<>(Servidor.class, ServidorDTO.class);
	}
	protected Extension<EnderecoDTO, Endereco> mappingEntityChild = new Extension<>(EnderecoDTO.class, Endereco.class);

	@Autowired
	private CargoService tS;
	@Autowired
	private EnderecoService eS;

	public ServidorController() {
	}

	@GetMapping(value = "/buscarTodosPorEquipe/{id_instituicao}")
	public ResponseEntity<Response<Page<ServidorDTO>>> buscarPorIdEquipePaginavel(@PathVariable("id_instituicao") Long id_instituicao,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "order", defaultValue = "id") String order,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "sort", defaultValue = "DESC") String sort) {
		PageRequest pageRequest = new PageRequest(page, size, Direction.valueOf(sort), order);
		Page<ServidorDTO> pageServidorDTO = mappingEntityToDTO
				.AsGenericMappingListPage(entityService.BuscarPorCargoInstituicaoIdPaginavel(id_instituicao, pageRequest));
		responsePage.setData(pageServidorDTO);
		return ResponseEntity.ok(responsePage);
	}

	@GetMapping(value = "/buscarTodosPorCargo/{idCargo}")
	public ResponseEntity<Response<Page<ServidorDTO>>> buscarPorIdCargoPaginavel(@PathVariable("idCargo") Long idCargo,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "order", defaultValue = "id") String order,
			@RequestParam(value = "size", defaultValue = "10") int size,
			@RequestParam(value = "sort", defaultValue = "DESC") String sort) {
		PageRequest pageRequest = new PageRequest(page, size, Direction.valueOf(sort), order);
		Page<ServidorDTO> pageServidorDTO = mappingEntityToDTO
				.AsGenericMappingListPage(entityService.BuscarPorCargoIdPaginavel(idCargo, pageRequest));
		responsePage.setData(pageServidorDTO);
		return ResponseEntity.ok(responsePage);
	}

	@GetMapping(value = "/buscarPorMatricula/{matriculaSiap}")
	public ResponseEntity<Response<ServidorDTO>> buscarPorMatricula(@PathVariable("matriculaSiap") String matriculaSiap) {
		log.info("Buscando servidor com a matrícula: {}", matriculaSiap);
		Optional<Servidor> servidor = entityService.BuscarPorMatriculaSiap(matriculaSiap);
		if (!servidor.isPresent()) {
			log.info("Servidor com a matrícula: {}, não cadastrado.", matriculaSiap);
			response.getErrors().add("Servidor não encontrado para a matrícula " + matriculaSiap);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(mappingEntityToDTO.AsGenericMapping(servidor.get()));
		return ResponseEntity.ok(response);
	}

	@PostMapping(value="/cadastrar")
	public ResponseEntity<Response<ServidorDTO>> cadastrarServidor(@Valid @RequestBody ServidorDTO servidorDTO,
			BindingResult result) throws NoSuchAlgorithmException {

		log.info("Cadastrando o servidor: {}", servidorDTO.toString());
		validarServidor(servidorDTO, result);
		if (result.hasErrors()) {
			log.error("Erro ao validar dados do novo servidor: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		Servidor servidor = mappingDTOToEntity.AsGenericMapping(servidorDTO);
		servidor.setSenha(PasswordUtils.GerarBCrypt(servidorDTO.getSenha()));
		List<Endereco> lista = servidor.getEndereco();
		servidor.setEndereco(new ArrayList<Endereco>());
		if(!lista.isEmpty())
		lista.forEach(endereco -> servidor.AdicionarEndereco(endereco));
		Optional<Cargo> cargo = this.tS.BuscarPorId(servidorDTO.getCargo().getId());
		cargo.ifPresent(t -> servidor.setCargo(t));
		this.entityService.Salvar(servidor);
		response.setData(mappingEntityToDTO.AsGenericMapping(servidor));
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
		Optional<Servidor> servidor = entityService.BuscarPorId(id);
		List<String> lista = new ArrayList<String>();
		if (!servidor.isPresent())
			result.addError(new ObjectError("Servidor", "Servidor não encontrado"));
		
		if (servidorDTO.getEmail() != null && !servidorDTO.getEmail().equals(servidor.get().getEmail())) {
			entityService.BuscarPorEmail(servidorDTO.getEmail())
					.ifPresent(al -> result.addError(new ObjectError("email", "Email já cadastrado")));
		}
		if (servidorDTO.getSenha() == null) {
			servidorDTO.setSenha(PasswordUtils.GerarBCrypt(servidorDTO.getSenha()));
		} else {
			lista.add("senha");
		}
		if (servidor.isPresent()) {
			lista.add("turma");
			lista.add("id");
			Servidor servidorEdit = mappingDTOToEntity.updateGeneric(servidorDTO, servidor.get(), lista);
			servidor.get().getEndereco().forEach(endereco -> this.eS.Deletar(endereco.getId()));
			if (servidorDTO.getCargo() != null && servidorDTO.getCargo().getId() > 0) {
				servidorEdit.setCargo(new Cargo());
				servidorEdit.getCargo().setId(servidorDTO.getCargo().getId());
			}
			if(servidor.get().getEndereco() != null && servidor.get().getEndereco().size() > 0) {
				List<Endereco> listaEndereco = servidor.get().getEndereco();
				servidor.get().setEndereco(new ArrayList<Endereco>());
					listaEndereco.forEach(endereco -> servidor.get().AdicionarEndereco(endereco));
			}
			this.entityService.Salvar(servidorEdit);
			response.setData(mappingEntityToDTO.AsGenericMapping(servidorEdit));
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
