package com.jvictorc.notas.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "notas")
data class Nota(
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy=GenerationType.UUID)
    val id: UUID? = null,
    @Column(nullable = false, length = 50)
    val titulo: String,
    @Column(nullable = false, length = 500)
    val descricao: String,
)