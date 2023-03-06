package com.jvictorc.forum.model

import jakarta.persistence.*
import jakarta.persistence.GenerationType

@Entity
data class Curso(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val nome: String,
    val categoria: String,
)
