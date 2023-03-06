package com.jvictorc.notas.dto.error

data class ErrorView(
    val error: String,
    val message: String?,
    val status: Int,
    val path: String
)