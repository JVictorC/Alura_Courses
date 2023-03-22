package com.jvictorc.volMed.dto.medico;

import com.jvictorc.volMed.dto.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record DadosCadastroMedicos(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Especialidade especialidade,
        @Valid
        @NotNull
        DadosEndereco endereco
) {
}
