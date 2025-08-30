package br.com.fiap.cp4_java_advanced.controller;

import br.com.fiap.cp4_java_advanced.dto.FerramentaPatchDTO;
import br.com.fiap.cp4_java_advanced.dto.FerramentaRequestDTO;
import br.com.fiap.cp4_java_advanced.dto.FerramentaResponseDTO;
import br.com.fiap.cp4_java_advanced.mapper.FerramentaMapper;
import br.com.fiap.cp4_java_advanced.modal.Ferramenta;
import br.com.fiap.cp4_java_advanced.service.FerramentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/ferramentas")
public class FerramentaController {

    @Autowired
    private FerramentaService service;

    @GetMapping
    public ResponseEntity<List<FerramentaResponseDTO>> listarTodos() {
        var ferramentas = service.listarTodos();

        ferramentas.forEach(this::adicionarLinks);

        return ResponseEntity.ok(ferramentas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FerramentaResponseDTO> buscarPorId(@PathVariable Long id) {
        var ferramenta = service.buscarPorId(id);

        adicionarLinks(ferramenta);

        return ResponseEntity.ok(ferramenta);
    }

    @PostMapping
    public ResponseEntity<FerramentaResponseDTO> salvar(@RequestBody @Valid FerramentaRequestDTO dto, UriComponentsBuilder uriBuilder) {
        var ferramenta = service.salvar(FerramentaMapper.toEntity(dto));

        adicionarLinks(ferramenta);

        var uri = uriBuilder.path("/ferramentas/{id}").buildAndExpand(ferramenta.getId()).toUri();

        return ResponseEntity.created(uri).body(ferramenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FerramentaResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid FerramentaRequestDTO dto) {
        var ferramentaAtualizada = service.atualizar(id, FerramentaMapper.toEntity(dto));

        adicionarLinks(ferramentaAtualizada);

        return ResponseEntity.ok(ferramentaAtualizada);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FerramentaResponseDTO> atualizarParcial(@PathVariable Long id, @RequestBody @Valid FerramentaPatchDTO dto) {
        var ferramentaAtualizada = FerramentaMapper.toDTO(service.atualizarParcial(id, dto));

        adicionarLinks(ferramentaAtualizada);

        return ResponseEntity.ok(ferramentaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    private FerramentaResponseDTO adicionarLinks(FerramentaResponseDTO ferramenta) {
        ferramenta.add(linkTo(methodOn(FerramentaController.class).buscarPorId(ferramenta.getId())).withRel("self"));

        ferramenta.add(linkTo(methodOn(FerramentaController.class).atualizar(ferramenta.getId(), null)).withRel("update"));

        ferramenta.add(linkTo(methodOn(FerramentaController.class).atualizarParcial(ferramenta.getId(), null)).withRel("partialUpdate"));

        ferramenta.add(linkTo(methodOn(FerramentaController.class).deletar(ferramenta.getId())).withRel("delete"));

        return ferramenta;
    }

}
