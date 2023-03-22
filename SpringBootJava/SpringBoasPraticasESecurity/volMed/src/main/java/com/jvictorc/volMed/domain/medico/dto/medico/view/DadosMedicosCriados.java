package com.jvictorc.volMed.domain.medico.dto.medico.view;

import com.jvictorc.volMed.domain.medico.dto.endereco.DadosEndereco;
import com.jvictorc.volMed.domain.medico.model.Especialidade;
import com.jvictorc.volMed.domain.medico.model.Medico;

import java.util.UUID;

public record DadosMedicosCriados(
        UUID id,
        String nome,
        String email,
        String crm,
        String telefone,
        Especialidade especialidade,
        DadosEndereco endereco
) {

    public DadosMedicosCriados(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), new DadosEndereco(medico.getEndereco()));
    }


}
