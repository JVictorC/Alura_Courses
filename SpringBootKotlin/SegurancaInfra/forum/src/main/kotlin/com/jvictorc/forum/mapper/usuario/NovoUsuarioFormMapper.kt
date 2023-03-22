package com.jvictorc.forum.mapper.usuario

import com.jvictorc.forum.dto.usuario.UsuarioForm
import com.jvictorc.forum.mapper.Mapper
import com.jvictorc.forum.model.Usuario

class NovoUsuarioFormMapper : Mapper<UsuarioForm, Usuario> {
    override fun map(u: UsuarioForm): Usuario {
        return Usuario(
            nome = u.nome,
            email = u.email,
            password = u.password,
        )
    }
}

