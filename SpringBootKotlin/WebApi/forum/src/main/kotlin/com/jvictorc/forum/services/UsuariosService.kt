package com.jvictorc.forum.services

import com.jvictorc.forum.model.Usuario
import org.springframework.stereotype.Service

@Service
class UsuariosService {
    private val listaUsuarios = mutableListOf<Usuario>(
        Usuario(
            id = 1,
            nome = "Victor",
            email = "victor@gmail.com"
        )
    )

    fun listarTodos() : List<Usuario> {
        return listaUsuarios
    }

    fun listaPorId(id: Long) : Usuario {
        return listaUsuarios.first { it.id == id }
    }
}
