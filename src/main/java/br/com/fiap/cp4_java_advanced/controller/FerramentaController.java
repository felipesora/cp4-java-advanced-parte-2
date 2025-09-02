package br.com.fiap.cp4_java_advanced.controller;

import br.com.fiap.cp4_java_advanced.modal.Ferramenta;
import br.com.fiap.cp4_java_advanced.modal.enums.Tamanho;
import br.com.fiap.cp4_java_advanced.service.FerramentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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

        if (ferramentas.isEmpty()) {
            if (tamanho == null && (nome == null || nome.isBlank())) {
                model.addAttribute("mensagemVazio", true);
            } else {
                model.addAttribute("mensagemFiltro", true);
            }
        }

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
