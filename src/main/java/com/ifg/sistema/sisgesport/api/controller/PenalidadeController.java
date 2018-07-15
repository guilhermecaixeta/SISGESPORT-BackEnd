package com.ifg.sistema.sisgesport.api.controller;

import com.ifg.sistema.sisgesport.api.controller.base.baseController;
import com.ifg.sistema.sisgesport.api.dto.PenalidadeDTO;
import com.ifg.sistema.sisgesport.api.entities.Penalidade;
import com.ifg.sistema.sisgesport.api.extesion.Extension;
import com.ifg.sistema.sisgesport.api.response.Response;
import com.ifg.sistema.sisgesport.api.services.PenalidadeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Optional;

public class PenalidadeController  extends baseController<PenalidadeDTO, Penalidade, PenalidadeService> {
    {
        mappingDTOToEntity = new Extension<>(PenalidadeDTO.class, Penalidade.class);
        mappingEntityToDTO = new Extension<>(Penalidade.class, PenalidadeDTO.class);
    }

    @GetMapping(value = "/BuscarTodos/{id_modalidade}")
    public ResponseEntity<Response<Page<PenalidadeDTO>>> BuscarTodos(
            @PathVariable("id_modalidade") Long id_modalidade,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "order", defaultValue = "id") String order,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "DESC") String sort) {
        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.valueOf(sort), order);
        Page<PenalidadeDTO> pagePenalidadeDTO = mappingEntityToDTO
                .AsGenericMappingListPage(entityService.BuscarPorModalidadeIdPaginavel(id_modalidade, pageRequest));
        responsePage.setData(pagePenalidadeDTO);
        return ResponseEntity.ok(responsePage);
    }

    @GetMapping(value = "/BuscarPorNome/{nome}")
    public ResponseEntity<Response<PenalidadeDTO>> BuscarPorNome(@PathVariable("nome") String nome) {
        entityOptional = entityService.BuscarPorNome(nome);
        if (entityOptional.isPresent()) {
            entityDTO = mappingEntityToDTO.AsGenericMapping(entityOptional.get());
            response.setData(entityDTO);
        } else
            response.getErrors().add("Penalidade com o nome" + nome + " não encontrado.");

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/BuscarPorId/{id}")
    public ResponseEntity<Response<PenalidadeDTO>> BuscarPorId(@PathVariable("id") Long id) {
        log.info("Buscando Penalidade com o id: {}", id);
        Optional<Penalidade> Penalidade = entityService.BuscarPorId(id);
        if (!Penalidade.isPresent()) {
            log.info("Instituição com o id: {}, não cadastrado.", id);
            response.getErrors().add("Instituição não encontrado para o id " + id);
            return ResponseEntity.badRequest().body(response);
        }
        response.setData(mappingEntityToDTO.AsGenericMapping(Penalidade.get()));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<PenalidadeDTO>> cadastrarPenalidade(@Valid @RequestBody PenalidadeDTO PenalidadeDTO,
                                                                 BindingResult result) throws NoSuchAlgorithmException {
        log.info("Cadastrando a Penalidade: {}", PenalidadeDTO.toString());
        if (result.hasErrors()) {
            log.error("Erro ao validar dados da nova Penalidade: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        entity = this.entityService.Salvar(mappingDTOToEntity.AsGenericMapping(PenalidadeDTO));
        response.setData(mappingEntityToDTO.AsGenericMapping(entity));
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Response<PenalidadeDTO>> atualizarPenalidade(@PathVariable("id") Long id,
                                                                 @Valid @RequestBody PenalidadeDTO PenalidadeDTO, BindingResult result) throws Exception {
        log.info("Atualizando dados da Penalidade: {}", PenalidadeDTO);
        entityOptional = this.entityService.BuscarPorId(id);
        if (!entityOptional.isPresent()) {
            return ResponseEntity.badRequest().body(response);
        } else {
            entity = mappingDTOToEntity.updateGeneric(PenalidadeDTO, entityOptional.get(), new ArrayList<String>());
        }
        entity = this.entityService.Salvar(entity);
        response.setData(mappingEntityToDTO.AsGenericMapping(entity));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response<String>> deletarPenalidade(@PathVariable("id") Long id) {
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
