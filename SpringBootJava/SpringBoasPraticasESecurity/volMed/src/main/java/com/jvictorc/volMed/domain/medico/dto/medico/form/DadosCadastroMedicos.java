package com.jvictorc.volMed.domain.medico.dto.medico.form;

import com.jvictorc.volMed.domain.medico.dto.endereco.DadosEndereco;
import com.jvictorc.volMed.domain.medico.model.Especialidade;
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
