package com.jvictorc.volMed.domain.medico.dto.medico.form;

import com.jvictorc.volMed.domain.medico.dto.endereco.DadosEndereco;
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
