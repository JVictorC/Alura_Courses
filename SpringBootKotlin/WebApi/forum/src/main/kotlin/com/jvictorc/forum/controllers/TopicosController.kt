package com.jvictorc.forum.controllers

import com.jvictorc.forum.dto.AtualizacaoTopicoForm
import com.jvictorc.forum.dto.NovoTopicoForm
import com.jvictorc.forum.dto.TopicoView
import com.jvictorc.forum.services.TopicosServices
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder


@RestController
@RequestMapping("/topicos")
class TopicosController(
    private val services: TopicosServices
) {

    @GetMapping
    fun listar(): List<TopicoView> {
        return services.getAllTopicos()
    }


    @GetMapping("/{id}")
    fun buscardId(@PathVariable id: String): TopicoView? {
        return services.getTopicoById(id.toLong())
    }


    @PostMapping
    fun cadastrar(
        @RequestBody @Valid topico: NovoTopicoForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicoView> {
        val topico = services.cadastrar(topico)

        val uri = uriBuilder.path("/topicos/${topico.id}").build().toUri()

        return ResponseEntity.created(uri).body(topico)
    }


    @PutMapping("/{id}")
    fun atualizaNota(
        @PathVariable id: String,
        @RequestBody @Valid topico: AtualizacaoTopicoForm
    ): ResponseEntity<TopicoView> {
        return ResponseEntity.ok(services.editarNotar(id, topico))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteNota(@PathVariable id: String) {
        return services.deleteNota(id)
    }


}