package com.jvictorc.forum.services

import com.jvictorc.forum.model.Curso
import org.springframework.stereotype.Service

@Service
class CursosServices {
    val listaCursos = mutableListOf<Curso>(
        Curso(
            id = 1,
            nome = "Kotlin",
            categoria = "Programação"
        )
    )


    fun listarTodos(): List<Curso> {
        return listaCursos
    }

    fun listaPorId(id: Long): Curso {
        return listaCursos.first { it.id == id }
    }
}