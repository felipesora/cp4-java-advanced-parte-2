package br.com.fiap.cp4_java_advanced.dto;

import br.com.fiap.cp4_java_advanced.modal.enums.Tamanho;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FerramentaPatchDTO {

    @Size(min = 3, max = 100, message = "O nome da ferramenta deve ter entre 3 e 100 caracteres.")
    private String nome;

    @Size(min = 3, max = 100, message = "O tipo da ferramenta deve ter entre 3 e 100 caracteres.")
    private String tipo;

    @Size(min = 3, max = 100, message = "A classificação da ferramenta deve ter entre 3 e 100 caracteres.")
    private String classificacao;

    private Tamanho tamanho;

    @Min(value = 0, message = "O preço da ferramenta deve ser maior que 0")
    private BigDecimal preco;

    @Min(value = 0, message = "A quantidade não pode ser negativa")
    private Integer quantidade;
}
