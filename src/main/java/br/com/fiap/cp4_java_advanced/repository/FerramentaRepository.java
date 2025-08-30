package br.com.fiap.cp4_java_advanced.repository;

import br.com.fiap.cp4_java_advanced.modal.Ferramenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FerramentaRepository extends JpaRepository<Ferramenta, Long> {
}
