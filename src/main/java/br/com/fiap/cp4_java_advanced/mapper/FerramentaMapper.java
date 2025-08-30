package br.com.fiap.cp4_java_advanced.mapper;

import br.com.fiap.cp4_java_advanced.dto.FerramentaRequestDTO;
import br.com.fiap.cp4_java_advanced.dto.FerramentaResponseDTO;
import br.com.fiap.cp4_java_advanced.modal.Ferramenta;

public class FerramentaMapper {

    public static FerramentaResponseDTO toDTO(Ferramenta ferramenta) {

        return new FerramentaResponseDTO(
                ferramenta.getId(),
                ferramenta.getNome(),
                ferramenta.getTipo(),
                ferramenta.getClassificacao(),
                ferramenta.getTamanho(),
                ferramenta.getPreco(),
                ferramenta.getQuantidade()
        );
    }

    public static Ferramenta toEntity(FerramentaRequestDTO dto) {
        Ferramenta ferramenta = new Ferramenta();
        ferramenta.setNome(dto.getNome());
        ferramenta.setTipo(dto.getTipo());
        ferramenta.setClassificacao(dto.getClassificacao());
        ferramenta.setTamanho(dto.getTamanho());
        ferramenta.setPreco(dto.getPreco());
        ferramenta.setQuantidade(dto.getQuantidade());

        return ferramenta;
    }

}
