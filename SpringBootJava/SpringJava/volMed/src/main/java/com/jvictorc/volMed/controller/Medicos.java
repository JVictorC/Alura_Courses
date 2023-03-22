package com.jvictorc.volMed.controller;

import com.jvictorc.volMed.dto.medico.DadosAtualizarMedicos;
import com.jvictorc.volMed.dto.medico.DadosCadastroMedicos;
import com.jvictorc.volMed.dto.medico.DadosMedicoListagem;
import com.jvictorc.volMed.exception.ErrorView;
import com.jvictorc.volMed.model.Medico;
import com.jvictorc.volMed.services.MedicoServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/medicos")
public class Medicos {
    @Autowired
    private MedicoServices medicoServices;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedicos dados) {

        medicoServices.cadastrasMedico(dados);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }


    @GetMapping
    public Page<DadosMedicoListagem> buscarTodosMerdicos(
            @PageableDefault(size = 10, sort = {"nome", "crm", "email"}) Pageable page
    ) {
        return medicoServices.buscarTodos(page);
    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizar(
            @RequestBody @Valid DadosAtualizarMedicos dadosAtualizarMedicos
            ) {
        medicoServices.atualizarMedico(dadosAtualizarMedicos);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(
            @PathVariable UUID id
    ) {
        medicoServices.deleteMedico(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }


/*
@ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorView> duplicateEmailException(HttpServletRequest req, DataIntegrityViolationException e) {
    }
*/
}
