package com.jvictorc.volMed.dto.medico;

import com.jvictorc.volMed.dto.endereco.DadosEndereco;
import com.jvictorc.volMed.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DadosAtualizarMedicos(
        @NotNull
        UUID id,

        String nome,

        String telefone,

        String email,

        DadosEndereco endereco
) {
}
