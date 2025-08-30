package br.com.fiap.cp4_java_advanced.dto;

import br.com.fiap.cp4_java_advanced.modal.enums.Tamanho;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FerramentaRequestDTO {

        @NotBlank(message = "nome da ferramenta é obrigatório")
        @Size(min = 3, max = 100, message = "O nome da ferramenta deve ter entre 3 e 100 caracteres.")
        private String nome;

        @NotBlank(message = "o tipo da ferramenta é obrigatório")
        private String tipo;

        @NotBlank(message = "a classificação da ferramenta é obrigatória")
        private String classificacao;

        @NotNull(message = "O tamanho da ferramenta é obrigatório")
        private Tamanho tamanho;

        @Min(value = 0, message = "O preço da ferramenta deve ser maior que 0")
        @NotNull(message = "O preço da ferramenta é obrigatório")
        private BigDecimal preco;

        @NotNull(message = "A quantidade é obrigatória")
        @Min(value = 0, message = "A quantidade não pode ser negativa")
        private Integer quantidade;
}

