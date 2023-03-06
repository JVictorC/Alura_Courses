package com.jvictorc.notas.mappers.form.notas

import com.jvictorc.notas.dto.notas.NotaForm
import com.jvictorc.notas.mappers.Mapper
import com.jvictorc.notas.model.Nota

class MapperFormToEntityNota : Mapper<NotaForm, Nota> {
    override fun map(formaInicial: NotaForm): Nota {
        return Nota(
            titulo = formaInicial.titulo,
            descricao = formaInicial.descricao
        )
    }

}