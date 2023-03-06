package com.jvictorc.forum.mapper.curso

import com.jvictorc.forum.dto.curso.CursoForm
import com.jvictorc.forum.mapper.Mapper
import com.jvictorc.forum.model.Curso

class NovoCursoFormMapper : Mapper<CursoForm, Curso> {
    override fun map(c: CursoForm): Curso {
        return Curso(
            nome = c.nome,
            categoria = c.categoria
        )
    }

}