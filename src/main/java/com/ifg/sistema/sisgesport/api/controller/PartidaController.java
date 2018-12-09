package com.ifg.sistema.sisgesport.api.controller;

import com.ifg.sistema.sisgesport.api.dto.PartidaDTO;
import com.ifg.sistema.sisgesport.api.entities.PageConfiguration;
import com.ifg.sistema.sisgesport.api.entities.Partida;
import com.ifg.sistema.sisgesport.api.extesion.Extension;
import com.ifg.sistema.sisgesport.api.response.Response;
import com.ifg.sistema.sisgesport.api.services.PartidaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.ifg.sistema.sisgesport.api.controller.base.baseController;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/partida")
public class PartidaController extends  baseController<PartidaDTO, Partida, PartidaService> {
    {
        listaExcecao.add("id");
        listaExcecao.add("serialVersionUID");
        mappingDTOToEntity = new Extension<>(PartidaDTO.class, Partida.class);
        mappingEntityToDTO = new Extension<>(Partida.class, PartidaDTO.class);
    }

        @GetMapping(value = "/BuscarPorEventoIdPaginavel/{id_evento}")
    public ResponseEntity<Response<Page<PartidaDTO>>> BuscarPorEventoIdPaginavel(
            @PathVariable("id_evento") Long id_evento,
            PageConfiguration pageConfig) {
        PageRequest pageRequest = new PageRequest(pageConfig.page, pageConfig.size, Sort.Direction.valueOf(pageConfig.sort), pageConfig.order);
        entityPageListDTO= mappingEntityToDTO
                .AsGenericMappingListPage(entityService.BuscarPorEventoIdPaginavel(id_evento, pageRequest));
        responsePage.setData(entityPageListDTO);
        return ResponseEntity.ok(responsePage);
    }

    @GetMapping(value = "/BuscarPorEventoIdEModalidadeId/{id_evento}/{id_modalidade}")
    public ResponseEntity<Response<List<PartidaDTO>>> BuscarPorEventoIdEModalidadeId
            (@PathVariable("id_evento") Long id_evento, @PathVariable("id_modalidade") Long id_modalidade)
    {
        Optional<List<Partida>> partidaLista =
                entityService.BuscarPorEventoIdEModalidadeId(id_evento, id_modalidade);
        if (partidaLista.isPresent()) {
            entityListDTO = mappingEntityToDTO.AsGenericMappingList(partidaLista .get(), false);
            responseList.setData(entityListDTO);
        } else
            response.getErrors().add("Nenhuma partida encontrada.");
        return ResponseEntity.ok(responseList);
    }

    @GetMapping(value = "/BuscarPorId/{id}")
    public ResponseEntity<Response<PartidaDTO>> BuscarPorId(@PathVariable("id") Long id) {
        log.info("Buscando Partida com o id: {}", id);
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
    public ResponseEntity<Response<PartidaDTO>> cadastrarPartida(@Valid @RequestBody PartidaDTO PartidaDTO,
                                                                       BindingResult result) throws NoSuchAlgorithmException {
        log.info("Cadastrando a Partida: {}", PartidaDTO.toString());
        if (result.hasErrors()) {
            log.error("Erro ao validar dados da nova Partida: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        entity = this.entityService.Salvar(mappingDTOToEntity.AsGenericMapping(PartidaDTO));
        response.setData(mappingEntityToDTO.AsGenericMapping(entity));
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Response<PartidaDTO>> atualizarPartida(@PathVariable("id") Long id,
                                                                       @Valid @RequestBody PartidaDTO PartidaDTO,
                                                                 BindingResult result) throws Exception {
        log.info("Atualizando dados da Partida: {}", PartidaDTO);
        entityOptional = this.entityService.BuscarPorId(id);
        if (!entityOptional.isPresent()) {
            return ResponseEntity.badRequest().body(response);
        } else {
            entity = mappingDTOToEntity.updateGeneric(PartidaDTO, entityOptional.get(), listaExcecao);
        }
        entity = this.entityService.Salvar(entity);
        response.setData(mappingEntityToDTO.AsGenericMapping(entity));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response<String>> deletarPartida(@PathVariable("id") Long id) {
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
