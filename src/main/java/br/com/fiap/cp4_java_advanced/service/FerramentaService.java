package br.com.fiap.cp4_java_advanced.service;

import br.com.fiap.cp4_java_advanced.modal.Ferramenta;
import br.com.fiap.cp4_java_advanced.modal.enums.Tamanho;
import br.com.fiap.cp4_java_advanced.repository.FerramentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FerramentaService {

    @Autowired
    private FerramentaRepository repository;

    public List<Ferramenta> listarFerramentas(Tamanho tamanho, String nome) {
        boolean temNome = nome != null && !nome.isBlank();
        boolean temTamanho = tamanho != null;

        if (temNome && temTamanho) {
            return repository.findByNomeContainingIgnoreCaseAndTamanhoOrderByIdAsc(nome, tamanho);
        } else if (temTamanho) {
            return repository.findByTamanhoOrderByIdAsc(tamanho);
        } else if (temNome) {
            return repository.findByNomeContainingIgnoreCaseOrderByIdAsc(nome);
        } else {
            return repository.findAllByOrderByIdAsc();
        }
    }

    public Ferramenta buscarPorId(Long id) {
        var ferramenta = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ferramenta com id: " + id + " não encontrada"));

        return ferramenta;
    }

    public Ferramenta salvar(Ferramenta ferramenta) {
        return repository.save(ferramenta);
    }

    public Ferramenta atualizar(Long id, Ferramenta ferramentaNova) {
            var ferramentaAtual = buscarPorId(id);

            ferramentaAtual.setNome(ferramentaNova.getNome());
            ferramentaAtual.setTipo(ferramentaNova.getTipo());
            ferramentaAtual.setClassificacao(ferramentaNova.getClassificacao());
            ferramentaAtual.setTamanho(ferramentaNova.getTamanho());
            ferramentaAtual.setPreco(ferramentaNova.getPreco());
            ferramentaAtual.setQuantidade(ferramentaNova.getQuantidade());

            return repository.save(ferramentaAtual);
    }

    public void deletar(Long id) {
        var ferramenta = buscarPorId(id);

        repository.delete(ferramenta);
    }
}
