package com.jvictorc.forum.mapper

import com.jvictorc.forum.dto.TopicoView
import com.jvictorc.forum.model.Topico
import org.springframework.stereotype.Component

@Component
class TopicoViewMapper : Mapper<Topico, TopicoView> {
    override fun map(t: Topico): TopicoView {
        return   TopicoView(
            id = t.id,
            titulo = t.titulo,
            mensagem = t.mensagem,
            status = t.status,
            dataCriacao = t.dataCriacao,
        )
    }
}