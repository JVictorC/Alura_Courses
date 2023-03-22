package com.jvictorc.volMed.domain.medico.dto.endereco;

import com.jvictorc.volMed.domain.medico.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        @NotBlank
        String uf,
        @NotBlank
        String cidade,
        String complemento,
        String numero
) {
    public DadosEndereco(Endereco endereco) {
        this(endereco.getLogradouro(), endereco.getBairro(), endereco.getCep(), endereco.getUf(), endereco.getCidade(), endereco.getComplemento(), endereco.getNumero());
    }
}
