package com.jvictorc.forum.controllers

import com.jvictorc.forum.dto.AtualizacaoTopicoForm
import com.jvictorc.forum.dto.NovoTopicoForm
import com.jvictorc.forum.dto.TopicoPorCategoriaDto
import com.jvictorc.forum.dto.TopicoView
import com.jvictorc.forum.services.TopicosServices
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder


@RestController
@RequestMapping("/topicos")
class TopicosController(
    private val services: TopicosServices
) {
    @GetMapping
    @Cacheable("topicos")
    fun listar(
        @RequestParam(required = false) nomeCurso: String?,
//        direction = Sort.Direction.DESC
        @PageableDefault(size = 5, sort = ["titulo"]) paginacacao: Pageable
    ): Page<TopicoView?> {
        return services.getAllTopicos(nomeCurso, paginacacao)
    }

    @GetMapping("/{id}")
    fun buscardId(@PathVariable id: String): TopicoView? {
        return services.getTopicoById(id.toLong())
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun cadastrar(
        @RequestBody @Valid topico: NovoTopicoForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicoView> {
        val topico = services.cadastrar(topico)

        val uri = uriBuilder.path("/topicos/${topico?.id}").build().toUri()

        return ResponseEntity.created(uri).body(topico)
    }


    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun atualizaNota(
        @PathVariable id: String,
        @RequestBody @Valid topico: AtualizacaoTopicoForm
    ): ResponseEntity<TopicoView> {
        return ResponseEntity.ok(services.editarTopico(id, topico))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @CacheEvict(value = ["topicos"], allEntries = true)
    fun deleteNota(@PathVariable id: String) {
        return services.deleteTopico(id)
    }



    @GetMapping("/relatorio")
    fun relatorio() : List<TopicoPorCategoriaDto> {
        return services.relatorio()
    }


}