package br.com.fiap.cp4_java_advanced.controller;

import br.com.fiap.cp4_java_advanced.dto.FerramentaPatchDTO;
import br.com.fiap.cp4_java_advanced.dto.FerramentaRequestDTO;
import br.com.fiap.cp4_java_advanced.dto.FerramentaResponseDTO;
import br.com.fiap.cp4_java_advanced.mapper.FerramentaMapper;
import br.com.fiap.cp4_java_advanced.modal.Ferramenta;
import br.com.fiap.cp4_java_advanced.modal.enums.Tamanho;
import br.com.fiap.cp4_java_advanced.service.FerramentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RequestMapping("/ferramentas")
public class FerramentaController {

    @Autowired
    private FerramentaService service;

    @GetMapping
    public String listarFerramentas(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Tamanho tamanho,
            Model model) {

        List<Ferramenta> ferramentas = service.listarFerramentas(tamanho, nome);

        model.addAttribute("ferramentas", ferramentas);
        return "ferramentas";
    }

    @GetMapping("/cadastrar")
    public String NovaFerramentaForm(Model model) {
        model.addAttribute("ferramenta", new Ferramenta());
        return "ferramenta-form-cadastro";
    }

    @PostMapping
    public String salvarFerramenta(Ferramenta ferramenta) {
        service.salvar(ferramenta);
        return "redirect:/ferramentas";
    }

    @GetMapping("/editar/{id}")
    public String editarFerramentaForm(@PathVariable Long id, Model model) {
        Ferramenta ferramenta = service.buscarPorId(id);
        model.addAttribute("ferramenta", ferramenta);
        return "ferramenta-form-editar";
    }

    @PostMapping("/editar/{id}")
    public String atualizarFerramenta(@PathVariable Long id, @ModelAttribute Ferramenta ferramenta) {
        service.atualizar(id, ferramenta);
        return "redirect:/ferramentas";
    }

    @GetMapping("/deletar/{id}")
    public String deletarFerramenta(@PathVariable Long id) {
        service.deletar(id);
        return "redirect:/ferramentas";
    }
}
