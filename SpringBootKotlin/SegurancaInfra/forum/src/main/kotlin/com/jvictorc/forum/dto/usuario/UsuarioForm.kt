package com.jvictorc.forum.dto.usuario

import jakarta.validation.constraints.NotEmpty

data class UsuarioForm(
    @field:NotEmpty
    val nome: String,
    @field:NotEmpty
    val email: String,
    @field:NotEmpty
    val password: String,
)