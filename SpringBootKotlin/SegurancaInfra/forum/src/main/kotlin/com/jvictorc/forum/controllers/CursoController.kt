package com.jvictorc.forum.controllers

import com.jvictorc.forum.dto.curso.CursoForm
import com.jvictorc.forum.model.Curso
import com.jvictorc.forum.services.CursosServices
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cursos")
class CursoController(
    private val service: CursosServices
) {

    @GetMapping
    fun getAll() : List<Curso> {
        return service.listarTodos()
    }


    @PostMapping
    fun createNewCurso(@Valid @RequestBody curso: CursoForm) : Curso {
        return service.criarCurso(curso)
    }

}