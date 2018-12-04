package com.ifg.sistema.sisgesport.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.ifg.sistema.sisgesport.api.entities.Aluno;
import com.ifg.sistema.sisgesport.api.entities.PageConfiguration;
import com.ifg.sistema.sisgesport.api.security.utils.JwtTokenUtil;
import com.ifg.sistema.sisgesport.api.services.AlunoService;
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
        listaExcecao.add("id");
        listaExcecao.add("time");
        listaExcecao.add("evento");
        listaExcecao.add("serialVersionUID");
		mappingDTOToEntity = new Extension<>(EquipeDTO.class, Equipe.class);
		mappingEntityToDTO = new Extension<>(Equipe.class, EquipeDTO.class);
	}
	@Autowired
	private ImagemService iS;
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

	@GetMapping(value = "/BuscarEquipePorIdEventoPaginavel/{id_evento}")
	public ResponseEntity<Response<Page<EquipeDTO>>> BuscarEquipePorIdEventoPaginavel(
			@PathVariable("id_evento") Long id_evento,
			PageConfiguration pageConfig) {
		PageRequest pageRequest = new PageRequest(pageConfig.page, pageConfig.size, Direction.valueOf(pageConfig.sort),
                pageConfig.order);
		entityPageListDTO = mappingEntityToDTO
				.AsGenericMappingListPage(entityService.BuscarEquipePorIdEventoPaginavel(id_evento, pageRequest));
        entityPageListDTO.forEach(x ->{
            if(x.getCapitao() != null) {
                x.getCapitao().setPerfil(null);
                x.getCapitao().setEquipe(null);
                x.getCapitao().setTurma(null);
                x.getCapitao().setEndereco(null);
                x.getCapitao().setImagem(null);
                x.getCapitao().setInstituicao(null);
                x.getCapitao().setSenha(null);
            }
            if(x.getAluno() != null)
                x.setAluno(null);
		    x.setTime(null);
		    x.setEvento(null);
        });
		responsePage.setData(entityPageListDTO);
		return ResponseEntity.ok(responsePage);
	}

	@GetMapping(value = "/BuscarEquipePorIdEvento/{id_evento}")
	public ResponseEntity<Response<List<EquipeDTO>>> BuscarEquipePorIdEvento(
			@PathVariable("id_evento") Long id_evento) {
		entityListDTO = mappingEntityToDTO
				.AsGenericMappingList(entityService.BuscarEquipePorIdEvento(id_evento).get(), false);
        entityListDTO.forEach(x ->{
            if(x.getCapitao() != null) {
                x.getCapitao().setEquipe(null);
                x.getCapitao().setPerfil(null);
                x.getCapitao().setEndereco(null);
                x.getCapitao().setSenha(null);
                x.getCapitao().setTurma(null);
                x.getCapitao().setImagem(null);
            }
            x.setAluno(null);
            x.setImagem(null);
			x.setTime(null);
			x.setEvento(null);
		});
		responseList.setData(entityListDTO);
		return ResponseEntity.ok(responseList);
	}

	@GetMapping(value = "/BuscarPorMatriculaCriador/{matriculaSiap}")
	public ResponseEntity<Response<EquipeDTO>> BuscarEquipePorIdEvento(
			@PathVariable("matriculaSiap") String matriculaSiap) {
		entityDTO = mappingEntityToDTO
				.AsGenericMapping(entityService.BuscarPorMatriculaCapitao(matriculaSiap).get());
		response.setData(entityDTO);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/BuscarPorCodigoEquipe/{codigo}")
	public ResponseEntity<Response<EquipeDTO>> BuscarPorCodigoEquipe(
			@PathVariable("codigo") String codigo) {
	    entityOptional = entityService.BuscarPorCodigoEquipe(codigo);
        if(!entityOptional.isPresent()){
            log.info("Equipe com o código: {}, não cadastrado.", codigo);
            response.getErrors().add("Equipe não encontrado para o código " + codigo);
            return ResponseEntity.badRequest().body(response);
        }
        String headerAuth = request.getHeader("Authorization").substring(7);
        String matricula = jwtTokenUtil .getUsernameFromToken(headerAuth);
        Aluno aluno = alunoService.BuscarPorMatricula(matricula).get();

        Optional<List<Equipe>> listaEquipes = entityService
                .BuscarEquipePorIdEvento(entityOptional.get().getEvento().getId());
        listaEquipes.get().forEach(x -> {
            x.getAluno().forEach(y -> {
                if(y.getMatricula() == aluno.getMatricula()){
                    log.info("Aluno com a matricula: {}, já possui eventos cadastrados.", aluno.getMatricula());
                    response.getErrors().add("Aluno já possui equipe cadastrada para esse evento!");
                }
            });
        });
        if(response.getErrors().size() > 0)
            return ResponseEntity.badRequest().body(response);
        entityDTO = mappingEntityToDTO.AsGenericMapping(entityOptional.get());
        response.setData(entityDTO);


        entityDTO.getEvento().setCriador(null);
        entityDTO.getEvento().getEventoModalidade().forEach(x -> {
            x.getModalidade().setPenalidade(null);
            x.getModalidade().setTipoPonto(null);
            x.getModalidade().setPosicao(null);
        });
        entityDTO.setAluno(null);
        entityDTO.setCapitao(null);
        entityDTO.setTime(null);
		return ResponseEntity.ok(response);
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

		entityDTO = mappingEntityToDTO.AsGenericMapping(entityOptional.get());
		if(entityDTO.getCapitao() != null){
            entityDTO.getCapitao().setInstituicao(null);
            entityDTO.getCapitao().setPerfil(null);
            entityDTO.getCapitao().setEndereco(null);
            entityDTO.getCapitao().getTurma().getCurso().setInstituicao(null);
            entityDTO.getCapitao().setSenha(null);
            entityDTO.getCapitao().setEquipe(null);
        }
        entityDTO.getEvento().getCriador().setSenha(null);
        entityDTO.getEvento().getCriador().setCargo(null);
        entityDTO.getEvento().getCriador().setEndereco(null);
        entityDTO.getEvento().getCriador().setPerfil(null);
        entityDTO.getEvento().getCriador().setImagem(null);
        entityDTO.getEvento().getEventoModalidade().forEach(x ->{
            x.getModalidade().setPenalidade(null);
            x.getModalidade().setTipoPonto(null);
            x.getModalidade().setPosicao(null);
        });
		entityDTO.getAluno().forEach(x ->{
		    x.setPerfil(null);
		    x.setSenha(null);
		    x.setInstituicao(null);
		    x.setImagem(null);
            x.setEquipe(null);
            x.setEmail(null);
            x.setEndereco(null);
        });
		response.setData(entityDTO);
		return ResponseEntity.ok(response);
	}

    @GetMapping(value = "/BuscarEquipePorIdAluno/{id_aluno}")
    public ResponseEntity<Response<List<EquipeDTO>>> BuscarEquipePorIdAluno
            (@PathVariable("id_aluno") Long id_aluno) {
        entityListDTO = mappingEntityToDTO
                .AsGenericMappingList(alunoService.BuscarPorId(id_aluno).get().getEquipe(), false);
        entityListDTO.forEach(data -> {
            data.setTime(null);
            data.setAluno(null);
            if(data.getCapitao() != null){
                data.getCapitao().setEquipe(null);
                data.getCapitao().setSenha(null);
                data.getCapitao().setTurma(null);
                data.getCapitao().setEndereco(null);
                data.getCapitao().setPerfil(null);
                data.getCapitao().setInstituicao(null);
                data.getCapitao().setMatricula(null);
            }
        });
        responseList.setData(entityListDTO);
        return ResponseEntity.ok(responseList);
    }

	@PostMapping
	public ResponseEntity<Response<EquipeDTO>> cadastrarEquipe(@Valid @RequestBody EquipeDTO equipeDTO,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando a equipe: {}", equipeDTO.toString());
		this.entityService.BuscarPorCodigoEquipe(equipeDTO.getCodigoEquipe())
				.ifPresent(inst -> result.addError(
				        new ObjectError("equipe", "Codigo equipe já cadastrado.")));
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
			entity = mappingDTOToEntity.updateGeneric(equipeDTO, entityOptional.get(), listaExcecao);
			this.entityService.Salvar(entity);
			response.setData(new EquipeDTO());
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
