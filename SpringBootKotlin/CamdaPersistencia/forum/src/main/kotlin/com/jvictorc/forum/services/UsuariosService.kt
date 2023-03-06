package com.jvictorc.forum.services

import com.jvictorc.forum.dto.usuario.UsuarioForm
import com.jvictorc.forum.exception.NotFoundExeption
import com.jvictorc.forum.mapper.curso.NovoCursoFormMapper
import com.jvictorc.forum.mapper.usuario.NovoUsuarioFormMapper
import com.jvictorc.forum.model.Usuario
import com.jvictorc.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service

@Service
class UsuariosService(
    private val usuarioRepository: UsuarioRepository
) {
    fun listarTodos() : List<Usuario> {
        return usuarioRepository.findAll()
    }

    fun listaPorId(id: Long) : Usuario {
        if(usuarioRepository.existsById(id)) return usuarioRepository.getReferenceById(id)

        throw NotFoundExeption("Curso Não Localizado")
    }

    fun createNewUser(newUser: UsuarioForm): Usuario {
        val user = NovoUsuarioFormMapper().map(newUser)
        usuarioRepository.save(user)
        return user
    }

    fun deleteAll() {
        usuarioRepository.deleteAll()
    }
}
