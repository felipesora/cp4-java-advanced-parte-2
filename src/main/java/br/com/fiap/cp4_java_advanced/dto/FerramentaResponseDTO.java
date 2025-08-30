package br.com.fiap.cp4_java_advanced.dto;

import br.com.fiap.cp4_java_advanced.modal.enums.Tamanho;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FerramentaResponseDTO extends RepresentationModel<FerramentaResponseDTO> {

    private Long id;

    private String nome;

    private String tipo;

    private String classificacao;

    private Tamanho tamanho;

    private BigDecimal preco;

    private Integer quantidade;
}
