package com.jvictorc.notas.controllers

import com.jvictorc.notas.dto.notas.NotaForm
import com.jvictorc.notas.model.Nota
import com.jvictorc.notas.services.NotasServices
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/notas")
class NotasController(
    private val notasServices: NotasServices
) {
    @GetMapping
    fun listarTodas(
        @PageableDefault(size = 5) pageable: Pageable
    ) : Page<Nota> {
        return notasServices.listAllNotas(pageable)
    }

    @GetMapping("/titulo/{titulo}")
    fun listarPorTitulo(
        @PathVariable("titulo") titulo: String
    ) : List<Nota> {
        return notasServices.listaPorTitulo(titulo)
    }

    @GetMapping("/{id}")
    fun listarPorId(
        @PathVariable("id") id: String
    ) : Nota {
        return notasServices.buscarPorId(id)
    }


    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    fun createNota(
        @RequestBody notaForm: NotaForm
    ) : Nota {
        return notasServices.createNewNote(notaForm)
    }



    @DeleteMapping("/{id}")
    fun deleteNota(
        @PathVariable("id") id: String,
    ) : ResponseEntity<Void> {
        notasServices.deleteNota(id)
        return ResponseEntity(HttpStatusCode.valueOf(204))
    }


    @PutMapping("/{id}")
    fun updateNota(
        @PathVariable id: String,
        @RequestBody notaForm: NotaForm,
        uriBuilder: UriComponentsBuilder
    ) : ResponseEntity<Nota> {
        val notaUpdated = notasServices.updateNota(notaForm, id)

        return ResponseEntity.status(HttpStatus.valueOf(202)).body(notaUpdated)
    }
}