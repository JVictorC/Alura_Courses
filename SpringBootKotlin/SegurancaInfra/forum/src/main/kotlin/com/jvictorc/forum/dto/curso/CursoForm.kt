package com.jvictorc.forum.dto.curso

import jakarta.validation.constraints.NotEmpty

data class CursoForm(
    @field:NotEmpty
    val nome: String,
    @field:NotEmpty
    val categoria: String,
)