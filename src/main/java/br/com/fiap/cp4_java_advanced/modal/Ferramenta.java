package br.com.fiap.cp4_java_advanced.modal;

import br.com.fiap.cp4_java_advanced.modal.enums.Tamanho;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "TDS_TB_Ferramentas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ferramenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 70)
    private String tipo;

    @Column(nullable = false, length = 70)
    private String classificacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 7)
    private Tamanho tamanho;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(nullable = false, precision = 10)
    private Integer quantidade;
}
