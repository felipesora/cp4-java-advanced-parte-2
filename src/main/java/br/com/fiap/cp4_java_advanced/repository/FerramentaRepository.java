package br.com.fiap.cp4_java_advanced.repository;

import br.com.fiap.cp4_java_advanced.modal.Ferramenta;
import br.com.fiap.cp4_java_advanced.modal.enums.Tamanho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FerramentaRepository extends JpaRepository<Ferramenta, Long> {
    List<Ferramenta> findAllByOrderByIdAsc();

    List<Ferramenta> findByTamanhoOrderByIdAsc(Tamanho tamanho);

    List<Ferramenta> findByNomeContainingIgnoreCaseOrderByIdAsc(String nome);

    List<Ferramenta> findByNomeContainingIgnoreCaseAndTamanhoOrderByIdAsc(String nome, Tamanho tamanho);
}
