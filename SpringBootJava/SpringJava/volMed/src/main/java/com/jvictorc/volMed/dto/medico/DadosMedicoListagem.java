package com.jvictorc.volMed.dto.medico;

import com.jvictorc.volMed.model.Medico;

import java.util.UUID;

public record DadosMedicoListagem(
        UUID id,
        String nome,
        String email,
        String crm,
        String telefone,
        Especialidade especialidade,

        boolean ativo
) {



    public DadosMedicoListagem(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getAtivo());
    }
}
