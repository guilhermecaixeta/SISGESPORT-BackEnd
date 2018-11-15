package com.ifg.sistema.sisgesport.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.ifg.sistema.sisgesport.api.dto.EventoModalidadeDTO;
import com.ifg.sistema.sisgesport.api.entities.*;
import com.ifg.sistema.sisgesport.api.security.utils.JwtTokenUtil;
import com.ifg.sistema.sisgesport.api.services.*;
import com.ifg.sistema.sisgesport.api.utils.EventoUtils;
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
import org.springframework.web.bind.annotation.GetMapping;

import com.ifg.sistema.sisgesport.api.controller.base.baseController;
import com.ifg.sistema.sisgesport.api.dto.EnderecoDTO;
import com.ifg.sistema.sisgesport.api.dto.EventoDTO;
import com.ifg.sistema.sisgesport.api.extesion.Extension;
import com.ifg.sistema.sisgesport.api.response.Response;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/evento")
public class EventoController extends baseController<EventoDTO, Evento, EventoService> {
	{
		mappingDTOToEntity = new Extension<>(EventoDTO.class, Evento.class);
		mappingEntityToDTO = new Extension<>(Evento.class, EventoDTO.class);
	}
	@Autowired
	private EnderecoService enderecoService;
	@Autowired
    private EventoModalidadeService eventoModalidadeService;
    @Autowired
    private EquipeService equipeService;
    @Autowired
    private TimeService timeService;
    @Autowired
	private ServidorService servidorService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private static final String TOKEN_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer";

	protected Extension<EnderecoDTO, Endereco> mappingEntityChild = new Extension<>(EnderecoDTO.class, Endereco.class);
    protected Extension<EventoModalidadeDTO, EventoModalidade> mappingEntityChildII = new Extension<>(EventoModalidadeDTO.class, EventoModalidade.class);
	
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
    @GetMapping(value = "/BuscarTodos")
    public ResponseEntity<Response<List<EventoDTO>>> BuscarTodos() {
        entityListDTO = mappingEntityToDTO
                .AsGenericMappingList(entityService.BuscarTodos(), false);
        responseList.setData(entityListDTO);
        return ResponseEntity.ok(responseList);
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
		log.info("Buscando evento com o id: {}", id);
		entityOptional = entityService.BuscarPorId(id);
		if (!entityOptional.isPresent()) {
			log.info("Evento com o id: {}, não cadastrado.", id);
			response.getErrors().add("Evento não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(mappingEntityToDTO.AsGenericMapping(entityOptional.get()));
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<EventoDTO>> CadastrarEvento(@Valid @RequestBody EventoDTO eventoDTO,
			BindingResult result, HttpServletRequest request) throws NoSuchAlgorithmException {
		log.info("Cadastrando a evento: {}", eventoDTO.toString());
        Optional<String> token = Optional.ofNullable(request.getHeader(TOKEN_HEADER));

        if (token.isPresent() && token.get().startsWith(BEARER_PREFIX)) {
            token = Optional.of(token.get().substring(7));
        }
		this.entityService.BuscarPorCodigoEvento(eventoDTO.getCodigoEvento())
				.ifPresent(inst -> result.addError(new ObjectError("evento", "Codigo evento já cadastrado.")));
		if (result.hasErrors()) {
			log.error("Erro ao validar dados da nova evento: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		entity = mappingDTOToEntity.AsGenericMapping(eventoDTO);
		String matricula =jwtTokenUtil.getUsernameFromToken(token.get());
		entity.setCriador(servidorService.BuscarPorMatriculaSiap(matricula).get());
		List<EventoModalidade> listaEventoModalidade= new ArrayList<EventoModalidade>();

        List<Endereco> listaEnderecos = entity.getEndereco();
        entity.setEndereco(new ArrayList<Endereco>());
        if (!listaEnderecos.isEmpty())
            listaEnderecos.forEach(endereco -> entity.AdicionarEndereco(endereco));

        if(entity.getEventoModalidade().size() > 0) {
            listaEventoModalidade.addAll(mappingEntityChildII.AsGenericMappingList(eventoDTO.getEventoModalidade(), true));
            entity.setEventoModalidade(new ArrayList<EventoModalidade>());
		}
		entity = this.entityService.Salvar(entity);
		listaEventoModalidade.forEach(x -> {
		    x.setEvento(entity);
		    x = eventoModalidadeService.Salvar(x);
		});

        entity.setEventoModalidade(listaEventoModalidade);
        EventoUtils eventoUtils = new EventoUtils();
        eventoUtils.GerarEquipes(entity.getQntEquipes(), entity).forEach(e -> equipeService.Salvar(e));

		response.setData(mappingEntityToDTO.AsGenericMapping(new Evento()));
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
			lista.add("id");
            entity = mappingDTOToEntity.AsGenericMapping(eventoDTO);
            List<Endereco> listaEnderecos = entity.getEndereco();

            entity.setEndereco(new ArrayList<Endereco>());

            if (!eventoDTO.getEndereco().isEmpty()) {
                entityOptional.get().getEndereco().forEach(endereco -> enderecoService.Deletar(endereco.getId()));
                listaEnderecos.forEach(endereco -> entity.AdicionarEndereco(endereco));
            }

			this.entityService.Salvar(entity);
			response.setData(new EventoDTO());
			return ResponseEntity.ok(response);
		}
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Response<String>> deletarEvento(@PathVariable("id") Long id) {
		Response<String> response = new Response<String>();
        entityOptional = this.entityService.BuscarPorId(id);
		if(!entityOptional.isPresent()) {
			log.info("Erro ao remover dados ligados ao id: {}", id);
			response.getErrors().add("Erro ao remover dado. Nenhum registro encontrado para o id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		Optional<List<Equipe>> listaEquipes = equipeService.BuscarEquipePorIdEvento(entityOptional.get().getId());
        if(listaEquipes.isPresent())
            listaEquipes.get().forEach(equipe -> {
                Optional<List<Time>> listaTimes = timeService.BuscarPorEquipeId(equipe.getId());
                if(listaTimes.isPresent())
                    listaTimes.get().forEach(time -> timeService.Deletar(time.getId()));
                equipeService.Deletar(equipe.getId());
            });
        entityOptional.get().getEventoModalidade().forEach(evento -> eventoModalidadeService.Deletar(evento.getId()));
		this.entityService.Deletar(id);
		return ResponseEntity.ok(new Response<String>());
	}
}
