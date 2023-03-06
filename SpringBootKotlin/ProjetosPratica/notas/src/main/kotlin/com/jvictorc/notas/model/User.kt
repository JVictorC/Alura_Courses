package com.jvictorc.notas.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import java.util.UUID

@Entity
@Table(name = "USERS")
data class User(
    @Id
    @Column(updatable = false, nullable = false, unique = true)
    @GeneratedValue(strategy= GenerationType.UUID)
    val id: UUID? = null,
    @Column(nullable = false, length = 50, unique = true)
    val username: String,
    @Column(nullable = false)
    var password: String,
)
