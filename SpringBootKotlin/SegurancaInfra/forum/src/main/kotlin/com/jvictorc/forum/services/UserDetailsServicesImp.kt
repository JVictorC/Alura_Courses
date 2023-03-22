package com.jvictorc.forum.services

import com.jvictorc.forum.exception.NotFoundExeption
import com.jvictorc.forum.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServicesImp(
    private val usuarioRepository: UsuarioRepository,
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val usuario = usuarioRepository.findByEmail(username) ?: throw NotFoundExeption("Usuario Nao Localizado")

        return UserDetail(
            usuario
        )
    }
}