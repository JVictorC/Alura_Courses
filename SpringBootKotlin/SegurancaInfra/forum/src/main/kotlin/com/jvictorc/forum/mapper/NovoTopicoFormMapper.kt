package com.jvictorc.forum.mapper

import com.jvictorc.forum.dto.NovoTopicoForm
import com.jvictorc.forum.model.Topico
import com.jvictorc.forum.services.CursosServices
import com.jvictorc.forum.services.UsuariosService
import org.springframework.stereotype.Component

@Component
class NovoTopicoFormMapper(
    private val cursosServices: CursosServices,
    private val usuariosService: UsuariosService,
) : Mapper<NovoTopicoForm, Topico>{
    override fun map(t: NovoTopicoForm): Topico {
        val curso = cursosServices.listaPorId(t.idCurso)
        val autor = usuariosService.listaPorId(t.idAutor)

        return Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = curso,
            autor = autor,
        )
    }

}
