package com.jvictorc.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class AtualizacaoTopicoForm (
    @field:NotEmpty
    val titulo: String,
    @field:NotEmpty
    val mensagem: String,
)
