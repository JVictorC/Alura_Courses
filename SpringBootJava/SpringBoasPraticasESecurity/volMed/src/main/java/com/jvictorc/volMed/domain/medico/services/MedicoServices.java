package com.jvictorc.volMed.domain.medico.services;

import com.jvictorc.volMed.domain.medico.dto.medico.form.DadosAtualizarMedicos;
import com.jvictorc.volMed.domain.medico.dto.medico.form.DadosCadastroMedicos;
import com.jvictorc.volMed.domain.medico.dto.medico.view.DadosMedicoListagem;
import com.jvictorc.volMed.domain.medico.dto.medico.view.DadosMedicosCriados;
import com.jvictorc.volMed.domain.medico.model.Medico;
import com.jvictorc.volMed.domain.medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class MedicoServices {
    @Autowired
    private MedicoRepository medicoRepository;

    public DadosMedicosCriados cadastrasMedico(DadosCadastroMedicos novoMedico) {
        Medico medico = new Medico(novoMedico);

        medicoRepository.save(medico);


        return new DadosMedicosCriados(medico);
    }

    public Page<DadosMedicoListagem> buscarTodos(Pageable page) {
        return medicoRepository.findAll(page).map(DadosMedicoListagem::new);
    }

    public DadosMedicosCriados atualizarMedico(DadosAtualizarMedicos dadosAtualizarMedicos) {
        final Medico medicoInDB = medicoRepository.findById(dadosAtualizarMedicos.id()).orElseThrow();


        medicoInDB.atualizarInfo(dadosAtualizarMedicos);


        return new DadosMedicosCriados(medicoInDB);
    }

    public void deleteMedico(UUID id) {
        final Medico medicoInDB = medicoRepository.getReferenceById(id);

        medicoInDB.desativaConta();

    }

    public DadosMedicosCriados buscarPeloId(UUID id) {
        var medico = medicoRepository.getReferenceById(id);
        return new DadosMedicosCriados(medico);
    }
}
