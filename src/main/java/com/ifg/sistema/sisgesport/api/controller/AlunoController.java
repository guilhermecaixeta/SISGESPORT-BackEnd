package com.ifg.sistema.sisgesport.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.ifg.sistema.sisgesport.api.dto.JogadorDTO;
import com.ifg.sistema.sisgesport.api.entities.*;
import com.ifg.sistema.sisgesport.api.enums.PerfilSistema;
import com.ifg.sistema.sisgesport.api.services.JogadorService;
import com.ifg.sistema.sisgesport.api.services.TimeService;
import com.ifg.sistema.sisgesport.api.utils.EventoUtils;
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
import org.springframework.web.bind.annotation.RestController;

import com.ifg.sistema.sisgesport.api.controller.base.baseController;
import com.ifg.sistema.sisgesport.api.dto.AlunoDTO;
import com.ifg.sistema.sisgesport.api.extesion.Extension;
import com.ifg.sistema.sisgesport.api.response.Response;
import com.ifg.sistema.sisgesport.api.services.AlunoService;
import com.ifg.sistema.sisgesport.api.services.EnderecoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/aluno")
public class AlunoController extends baseController<AlunoDTO, Aluno, AlunoService> {
	{
        listaExcecao.add("serialVersionUID");
        listaExcecao.add("senha");
        listaExcecao.add("turma");
        listaExcecao.add("id");
		mappingDTOToEntity = new Extension<>(AlunoDTO.class, Aluno.class);
		mappingEntityToDTO = new Extension<>(Aluno.class, AlunoDTO.class);
	}
	private Extension<JogadorDTO, Jogador> mappingDTOToEntityII = new Extension<JogadorDTO, Jogador>(JogadorDTO.class, Jogador.class);
	@Autowired
	private EnderecoService eS;
	@Autowired
	private TimeService timeService;
	@Autowired
	private JogadorService jogadorService;

	public AlunoController() {}

	@GetMapping(value = "/BuscarPorIdEquipePaginavel/{id_equipe}")
	public ResponseEntity<Response<Page<AlunoDTO>>> BuscarPorIdEquipePaginavel
			(@PathVariable("id_equipe") Long id_equipe, PageConfiguration pageConfig) {
		PageRequest pageRequest = new PageRequest(pageConfig.page, pageConfig.size, Direction.valueOf(pageConfig.sort), pageConfig.order);
		entityPageListDTO = mappingEntityToDTO
				.AsGenericMappingListPage(entityService.BuscarPorIdEquipePaginavel(id_equipe, pageRequest));
        entityPageListDTO.forEach(data -> data.setSenha(null));
		responsePage.setData(entityPageListDTO);
		return ResponseEntity.ok(responsePage);
	}

	@GetMapping(value = "/BuscarPorIdEquipe/{id_equipe}")
	public ResponseEntity<Response<List<AlunoDTO>>> BuscarPorId(@PathVariable("id_equipe") Long id_equipe) {
		entityListDTO = mappingEntityToDTO
				.AsGenericMappingList(entityService.BuscarPorIdEquipe(id_equipe).get(), false);
		entityListDTO.forEach(data -> {
		    data.setSenha(null);
		    data.setEquipe(null);
		    data.setEndereco(null);
		    data.setPerfil(null);
		    data.setInstituicao(null);
		});
		responseList.setData(entityListDTO);
		return ResponseEntity.ok(responseList);
	}

	@GetMapping(value = "/BuscarPorIdTurma/{id_turma}")
	public ResponseEntity<Response<List<AlunoDTO>>> BuscarPorIdTurma(@PathVariable("id_turma") Long id_turma) {
		entityListDTO = mappingEntityToDTO
				.AsGenericMappingList(entityService.BuscarPorIdTurma(id_turma).get(), false);
        entityListDTO.forEach(data -> data.setSenha(null));
		responseList.setData(entityListDTO);
		return ResponseEntity.ok(responseList);
	}

    @GetMapping(value = "/BuscarPorIdTurmaEEvento/{id_turma}/{id_evento}")
    public ResponseEntity<Response<List<AlunoDTO>>> BuscarPorIdTurma
            (@PathVariable("id_turma") Long id_turma, @PathVariable("id_evento") Long id_evento) {
	    List<AlunoDTO> listaRemocao = new ArrayList<AlunoDTO>();
	    Optional<List<Aluno>> lis = entityService.BuscarPorIdTurma(id_turma);
        entityListDTO = mappingEntityToDTO
                .AsGenericMappingList(lis.get(), false);

        entityListDTO.forEach(data -> {
            data.getEquipe().forEach(equipe ->{
                if(equipe.getEvento().getId() == id_evento)
                    listaRemocao.add(data);
            });
            data.setInstituicao(null);
            data.setEndereco(null);
            data.setPerfil(null);
            data.setEquipe(null);
            data.setSenha(null);
        });
        entityListDTO.removeAll(listaRemocao);
        responseList.setData(entityListDTO);
        return ResponseEntity.ok(responseList);
    }

    @GetMapping(value = "/BuscarPorIdTurmaPaginavel/{id_turma}")
	public ResponseEntity<Response<Page<AlunoDTO>>> BuscarPorIdTurmaPaginavel
            (@PathVariable("id_turma") Long id_turma, PageConfiguration pageConfig) {
		PageRequest pageRequest =
                new PageRequest(pageConfig.page, pageConfig.size, Direction.valueOf(pageConfig.sort), pageConfig.order);
        entityPageListDTO = mappingEntityToDTO
				.AsGenericMappingListPage(entityService.BuscarPorIdTurmaPaginavel(id_turma, pageRequest));
        entityPageListDTO.forEach(data -> data.setSenha(null));
		responsePage.setData(entityPageListDTO);
		return ResponseEntity.ok(responsePage);
	}

	@GetMapping(value = "/BuscarPorMatricula/{matricula}")
	public ResponseEntity<Response<AlunoDTO>> BuscarPorMatricula(@PathVariable("matricula") String matricula) {
		log.info("Buscando aluno com a matrícula: {}", matricula);
		entityOptional = entityService.BuscarPorMatricula(matricula);
		if (!entityOptional.isPresent()) {
			log.info("Aluno com a matrícula: {}, não cadastrado.", matricula);
			response.getErrors().add("Aluno não encontrado para a matrícula " + matricula);
			return ResponseEntity.badRequest().body(response);
		}
		entityOptional.get().setSenha(null);
        entityOptional.get().setEquipe(null);
        entityOptional.get().setEndereco(null);
		response.setData(mappingEntityToDTO.AsGenericMapping(entityOptional.get()));
		return ResponseEntity.ok(response);
	}

	@PostMapping()
	public ResponseEntity<Response<AlunoDTO>> cadastrarAluno(@Valid @RequestBody AlunoDTO alunoDTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando o aluno: {}", alunoDTO.getNome());
		validarAluno(alunoDTO, result);
		if (result.hasErrors()) {
			log.error("Erro ao validar dados do novo aluno: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		entity = mappingDTOToEntity.AsGenericMapping(alunoDTO);
        entity.setPerfil(PerfilSistema.ROLE_USUARIO);
		List<Endereco> lista = entity.getEndereco();
		entity.setEndereco(new ArrayList<Endereco>());
		if (!lista.isEmpty())
			lista.forEach(endereco -> entity.AdicionarEndereco(endereco));

		this.entityService.Salvar(entity);
		response.setData(mappingEntityToDTO.AsGenericMapping(entity));
		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "AdicionarJogador")
	public ResponseEntity<Response<AlunoDTO>> adicionar(@Valid @RequestBody JogadorDTO jogadorDTO,
														BindingResult result) throws Exception {
		response = new Response<>();
		EventoUtils eventoUtils = new EventoUtils();
		log.info("Atualizando dados do Aluno com o id: {}", jogadorDTO.getJogador().getId());
		entityOptional = entityService.BuscarPorId(jogadorDTO.getJogador().getId());
		Optional<Time> time = timeService.BuscarPorId(jogadorDTO.getTime().getId());
		if(!time.isPresent() || !entityOptional.isPresent()) {
			response.getErrors().add("Erro ao adicionar o aluno ao time!");
			return ResponseEntity.badRequest().body(response);
		}else{
			response = eventoUtils.ValidarJogadorTime(time.get(), entityOptional.get(), response);
		}
		if(response.getErrors().size() > 0){
			return ResponseEntity.badRequest().body(response);
		}else {
			jogadorService.Salvar(mappingDTOToEntityII.AsGenericMapping(jogadorDTO));
			response.setData(new AlunoDTO());
		}
		return ResponseEntity.ok(response);
	}

	private void validarAluno(AlunoDTO alunoDTO, BindingResult result) {
		this.entityService.BuscarPorMatricula(alunoDTO.getMatricula())
				.ifPresent(alu -> result.addError(new ObjectError("aluno", "Matrícula já cadastrada.")));

		this.entityService.BuscarPorEmail(alunoDTO.getEmail())
				.ifPresent(alu -> result.addError(new ObjectError("aluno", "Email já cadastrado.")));
	}

	@GetMapping(value = "/AdicionarEquipe/{id_aluno}/{id_equipe}")
	public ResponseEntity<Response<AlunoDTO>> AdicionarEquipe(
	        @PathVariable("id_aluno") Long id_aluno, @PathVariable("id_equipe") Long id_equipe) throws Exception {
		log.info("Atualizando dados do Aluno: {}", id_aluno);
		entityOptional = entityService.BuscarPorId(id_aluno);

		if (!entityOptional.isPresent()) {
            return ResponseEntity.badRequest().body(response);
        } else {
            entity = entityOptional.get();
            List<Equipe> equipeLista= new ArrayList<>();
            Equipe equipe = new Equipe();
            equipe.setId(id_equipe);
            equipeLista.add(equipe);
            if(entity.getEquipe().size() > 0)
                entity.getEquipe().forEach(x -> equipeLista.add(x));
            entity.setEquipe(equipeLista);

			this.entityService.Salvar(entity);
			response.setData(mappingEntityToDTO.AsGenericMapping(new Aluno()));
            return ResponseEntity.ok(response);
        }
	}

    @PutMapping(value = "/{id}")
    public ResponseEntity<Response<AlunoDTO>> atualizarAluno(@PathVariable("id") Long id,
                                                             @Valid @RequestBody AlunoDTO alunoDTO,
															 BindingResult result) throws Exception {
        log.info("Atualizando dados do Aluno: {}", alunoDTO);
        Optional<Aluno> aluno = entityService.BuscarPorId(id);

        if (!aluno.isPresent())
            result.addError(new ObjectError("Aluno", "Aluno não encontrado"));

        if (alunoDTO.getEmail() != null && !alunoDTO.getEmail().equals(aluno.get().getEmail())) {
            entityService.BuscarPorEmail(alunoDTO.getEmail())
                    .ifPresent(al -> result.addError(new ObjectError("email", "Email já cadastrado")));
        }
        if (aluno.isPresent()) {
            entity = mappingDTOToEntity.updateGeneric(alunoDTO, aluno.get(), listaExcecao);
            aluno.get().getEndereco().forEach(endereco -> this.eS.Deletar(endereco.getId()));
            if (alunoDTO.getTurma() != null && alunoDTO.getTurma().getId() > 0) {
                entity.setTurma(new Turma());
                entity.getTurma().setId(alunoDTO.getTurma().getId());
            }
            if (!alunoDTO.getEndereco().isEmpty()) {
                List<Endereco> listaEndereco = aluno.get().getEndereco();
                aluno.get().setEndereco(new ArrayList<Endereco>());
                listaEndereco.forEach(endereco -> aluno.get().AdicionarEndereco(endereco));
            }
            this.entityService.Salvar(entity);
            response.setData(mappingEntityToDTO.AsGenericMapping(entity));
        } else {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> deletarAluno(@PathVariable("id") Long id) {
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
