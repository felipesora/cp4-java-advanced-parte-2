package br.com.fiap.cp4_java_advanced.service;

import br.com.fiap.cp4_java_advanced.dto.FerramentaPatchDTO;
import br.com.fiap.cp4_java_advanced.dto.FerramentaRequestDTO;
import br.com.fiap.cp4_java_advanced.dto.FerramentaResponseDTO;
import br.com.fiap.cp4_java_advanced.mapper.FerramentaMapper;
import br.com.fiap.cp4_java_advanced.modal.Ferramenta;
import br.com.fiap.cp4_java_advanced.repository.FerramentaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FerramentaService {

    @Autowired
    private FerramentaRepository repository;

    public List<FerramentaResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(FerramentaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FerramentaResponseDTO buscarPorId(Long id) {
        var ferramenta = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ferramenta com id: " + id + " não encontrada"));

        return FerramentaMapper.toDTO(ferramenta);
    }

    public FerramentaResponseDTO salvar(Ferramenta ferramenta) {
        var ferramentaSalva = repository.save(ferramenta);
        return FerramentaMapper.toDTO(ferramentaSalva);
    }

    public FerramentaResponseDTO atualizar(Long id, Ferramenta ferramentaNova) {
            var ferramentaAtual = repository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Ferramenta com id: " + id + " não encontrada"));

            ferramentaAtual.setNome(ferramentaNova.getNome());
            ferramentaAtual.setTipo(ferramentaNova.getTipo());
            ferramentaAtual.setClassificacao(ferramentaNova.getClassificacao());
            ferramentaAtual.setTamanho(ferramentaNova.getTamanho());
            ferramentaAtual.setPreco(ferramentaNova.getPreco());
            ferramentaAtual.setQuantidade(ferramentaNova.getQuantidade());

            var atualizada = repository.save(ferramentaAtual);

            return FerramentaMapper.toDTO(atualizada);
    }

    public Ferramenta atualizarParcial(Long id, FerramentaPatchDTO dto) {
        var ferramenta = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ferramenta com id: " + id + " não encontrada"));

        if (dto.getNome() != null) ferramenta.setNome(dto.getNome());
        if (dto.getTipo() != null) ferramenta.setTipo(dto.getTipo());
        if (dto.getClassificacao() != null) ferramenta.setClassificacao(dto.getClassificacao());
        if (dto.getTamanho() != null) ferramenta.setTamanho(dto.getTamanho());
        if (dto.getPreco() != null) ferramenta.setPreco(dto.getPreco());
        if (dto.getQuantidade() != null) ferramenta.setQuantidade(dto.getQuantidade());

        return repository.save(ferramenta);
    }

    public void deletar(Long id) {
        var ferramenta = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ferramenta com id: " + id + " não encontrada"));

        repository.delete(ferramenta);
    }
}
