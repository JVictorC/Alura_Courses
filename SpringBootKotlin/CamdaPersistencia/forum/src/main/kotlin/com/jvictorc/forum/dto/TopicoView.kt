package com.jvictorc.forum.dto

import com.jvictorc.forum.model.StatusTopico
import java.time.LocalDateTime

data class TopicoView(
    val id: Long? = null,
    val titulo: String,
    val mensagem: String,
    val status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
)