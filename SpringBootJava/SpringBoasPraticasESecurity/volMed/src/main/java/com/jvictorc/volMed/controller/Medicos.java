package com.jvictorc.volMed.controller;

import com.jvictorc.volMed.domain.medico.dto.medico.form.DadosAtualizarMedicos;
import com.jvictorc.volMed.domain.medico.dto.medico.form.DadosCadastroMedicos;
import com.jvictorc.volMed.domain.medico.dto.medico.view.DadosMedicoListagem;
import com.jvictorc.volMed.domain.medico.dto.medico.view.DadosMedicosCriados;
import com.jvictorc.volMed.domain.medico.services.MedicoServices;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/medicos")
public class Medicos {
    @Autowired
    private MedicoServices medicoServices;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosMedicosCriados> cadastrar(@RequestBody @Valid DadosCadastroMedicos dados, UriComponentsBuilder uriBuilder) {

        DadosMedicosCriados dadosMedicosCriados = medicoServices.cadastrasMedico(dados);

        var uri = uriBuilder.path("medicos/{id}").buildAndExpand(dadosMedicosCriados.id()).toUri();

        return ResponseEntity.created(uri).body(dadosMedicosCriados);
    }



    @GetMapping("/{id}")
    public ResponseEntity<DadosMedicosCriados> buscaPeloId(
            @PathVariable UUID id
    ) {
        var medico = medicoServices.buscarPeloId(id);



        return ResponseEntity.ok(medico);
    }

    @GetMapping
    public ResponseEntity<Page<DadosMedicoListagem>> buscarTodosMerdicos(
            @PageableDefault(size = 10, sort = {"nome", "crm", "email"}) Pageable page
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(medicoServices.buscarTodos(page));
    }


    @PutMapping
    @Transactional
    public ResponseEntity<DadosMedicosCriados> atualizar(
            @RequestBody @Valid DadosAtualizarMedicos dadosAtualizarMedicos
            ) {
        DadosMedicosCriados dadosMedicosCriados = medicoServices.atualizarMedico(dadosAtualizarMedicos);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dadosMedicosCriados);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(
            @PathVariable UUID id
    ) {
        medicoServices.deleteMedico(id);

        return ResponseEntity.noContent().build();
    }


/*
@ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorView> duplicateEmailException(HttpServletRequest req, DataIntegrityViolationException e) {
    }
*/
}
