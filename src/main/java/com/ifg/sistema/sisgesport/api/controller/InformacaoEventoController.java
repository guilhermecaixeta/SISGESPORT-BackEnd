package com.ifg.sistema.sisgesport.api.controller;

import com.ifg.sistema.sisgesport.api.controller.base.baseController;
import com.ifg.sistema.sisgesport.api.dto.InformacaoEventoDTO;
import com.ifg.sistema.sisgesport.api.entities.InformacaoEvento;
import com.ifg.sistema.sisgesport.api.entities.PageConfiguration;
import com.ifg.sistema.sisgesport.api.extesion.Extension;
import com.ifg.sistema.sisgesport.api.response.Response;
import com.ifg.sistema.sisgesport.api.services.InformacaoEventoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/informacaoEvento")
public class InformacaoEventoController extends baseController<InformacaoEventoDTO, InformacaoEvento, 
        InformacaoEventoService> {
    {
        listaExcecao.add("id");
        listaExcecao.add("serialVersionUID");
        mappingDTOToEntity = new Extension<>(InformacaoEventoDTO.class, InformacaoEvento.class);
        mappingEntityToDTO = new Extension<>(InformacaoEvento.class, InformacaoEventoDTO.class);
    }

    @GetMapping(value = "/BuscarPorEventoIdPaginavel/{id_evento}")
    public ResponseEntity<Response<Page<InformacaoEventoDTO>>> BuscarPorEventoIdPaginavel(
            @PathVariable("id_evento") Long id_evento, PageConfiguration pageConfig) {
        PageRequest pageRequest = new PageRequest(pageConfig.page, pageConfig.size,
                Sort.Direction.valueOf(pageConfig.sort), pageConfig.order);
        entityPageListDTO = mappingEntityToDTO
                .AsGenericMappingListPage(entityService.BuscarPorEventoIdPaginavel(id_evento, pageRequest));
        responsePage.setData(entityPageListDTO);
        return ResponseEntity.ok(responsePage);
    }

    @GetMapping(value = "/BuscarPorCodigoEventoPaginavel/{codigo_equipe}")
    public ResponseEntity<Response<Page<InformacaoEventoDTO>>> BuscarPorCodigoEventoPaginavel(
            @PathVariable("codigo_equipe") String codigo_equipe, PageConfiguration pageConfig) {
        PageRequest pageRequest = new PageRequest(pageConfig.page, pageConfig.size,
                Sort.Direction.valueOf(pageConfig.sort), pageConfig.order);
        entityPageListDTO = mappingEntityToDTO
                .AsGenericMappingListPage(entityService.BuscarPorCodigoEventoPaginavel(codigo_equipe, pageRequest));
        responsePage.setData(entityPageListDTO);
        return ResponseEntity.ok(responsePage);
    }

    @GetMapping(value = "/BuscarPorCodigoEvento/{codigo_evento}")
    public ResponseEntity<Response<List<InformacaoEventoDTO>>> BuscarPorIdEstado(@PathVariable("codigo_evento")
                                                                                             String codigo_evento) {
        Optional<List<InformacaoEvento>> lista = entityService.BuscarPorCodigoEvento(codigo_evento);
        if (lista.isPresent()) {
            entityListDTO = mappingEntityToDTO.AsGenericMappingList(lista.get(), false);
            responseList.setData(entityListDTO);
        } else
            responseList.getErrors().add("Informação com o código do evento " + codigo_evento+ " não encontrado.");

        return ResponseEntity.ok(responseList);
    }

    @GetMapping(value = "/BuscarPorId/{id}")
    public ResponseEntity<Response<InformacaoEventoDTO>> BuscarPorId(@PathVariable("id") Long id) {
        log.info("Buscando municipio com o id: {}", id);
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
    public ResponseEntity<Response<InformacaoEventoDTO>> cadastrarInformacaoEvento(@Valid @RequestBody
       InformacaoEventoDTO informacao, BindingResult result) throws NoSuchAlgorithmException {
        log.info("Cadastrando a municipio: {}", informacao.getTitulo());
        if (result.hasErrors()) {
            log.error("Erro ao validar dados da nova informacao: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        entity = mappingDTOToEntity.AsGenericMapping(informacao);
        response.setData(mappingEntityToDTO.AsGenericMapping(this.entityService.Salvar(entity)));
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Response<InformacaoEventoDTO>> atualizarInformacaoEvento(@PathVariable("id") Long id,
                                                                     @Valid @RequestBody InformacaoEventoDTO municipioDTO, BindingResult result) throws Exception {
        log.info("Atualizando dados do municipio: {}", municipioDTO);
        entityOptional = this.entityService.BuscarPorId(id);
        if (!entityOptional.isPresent()) {
            return ResponseEntity.badRequest().body(response);
        } else {
            entity = mappingDTOToEntity.updateGeneric(municipioDTO, entityOptional.get(), listaExcecao);
        }
        entity = this.entityService.Salvar(entity);
        response.setData(new InformacaoEventoDTO());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response<String>> deletarInformacaoEvento(@PathVariable("id") Long id) {
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
