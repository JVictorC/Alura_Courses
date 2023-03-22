package com.jvictorc.volMed.services;

import com.jvictorc.volMed.dto.medico.DadosAtualizarMedicos;
import com.jvictorc.volMed.dto.medico.DadosCadastroMedicos;
import com.jvictorc.volMed.dto.medico.DadosMedicoListagem;
import com.jvictorc.volMed.model.Medico;
import com.jvictorc.volMed.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class MedicoServices {
    @Autowired
    private MedicoRepository medicoRepository;

    public void cadastrasMedico(DadosCadastroMedicos novoMedico) {
        medicoRepository.save(new Medico(novoMedico));
    }

    public Page<DadosMedicoListagem> buscarTodos(Pageable page) {
        return medicoRepository.findAll(page).map(DadosMedicoListagem::new);
    }

    public void atualizarMedico(DadosAtualizarMedicos dadosAtualizarMedicos) {
        final Medico medicoInDB = medicoRepository.getReferenceById(dadosAtualizarMedicos.id());


        medicoInDB.atualizarInfo(dadosAtualizarMedicos);
    }

    public void deleteMedico(UUID id) {
        final Medico medicoInDB = medicoRepository.getReferenceById(id);

        medicoInDB.desativaConta();

    }
}
