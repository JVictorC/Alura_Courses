package com.jvictorc.forum.model


import com.jvictorc.forum.model.Topico
import com.jvictorc.forum.model.Usuario
import java.time.LocalDateTime

data class Resposta (
    val id: Long? = null,
    val mensagem: String,
    val dataCraicao: LocalDateTime = LocalDateTime.now(),
    val autor: Usuario,
    val topico: Topico,
    val solucao: Boolean,
)
