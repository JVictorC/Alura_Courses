package com.jvictorc.forum.dto

import jakarta.validation.constraints.*


data class NovoTopicoForm(
    @field:NotEmpty
    val titulo: String,
    @field:NotEmpty
    val mensagem: String,
    @field:NotNull
    val idCurso: Long,
    @field:NotNull
    val idAutor: Long,
)
