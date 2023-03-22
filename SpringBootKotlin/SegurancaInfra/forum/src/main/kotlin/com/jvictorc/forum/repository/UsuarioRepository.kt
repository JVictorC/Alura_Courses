package com.jvictorc.forum.repository

import com.jvictorc.forum.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository : JpaRepository<Usuario, Long> {

    fun findByEmail(email: String?) : Usuario?

}