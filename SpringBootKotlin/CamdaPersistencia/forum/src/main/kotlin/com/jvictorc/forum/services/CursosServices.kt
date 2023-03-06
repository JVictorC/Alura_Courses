package com.jvictorc.forum.services

import com.jvictorc.forum.dto.curso.CursoForm
import com.jvictorc.forum.exception.NotFoundExeption
import com.jvictorc.forum.mapper.curso.NovoCursoFormMapper
import com.jvictorc.forum.model.Curso
import com.jvictorc.forum.repository.CursoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CursosServices(
    private val cursoRepository: CursoRepository
) {
    fun listarTodos(): List<Curso> {
        return cursoRepository.findAll()
    }

    fun listaPorId(id: Long): Curso {
        if(cursoRepository.existsById(id)) return cursoRepository.getReferenceById(id)

        throw NotFoundExeption("Curso Não Localizado")
    }

    fun criarCurso(cursoForm: CursoForm): Curso {
        val curso = NovoCursoFormMapper().map(cursoForm)

        cursoRepository.save(curso)

        return curso
    }
}