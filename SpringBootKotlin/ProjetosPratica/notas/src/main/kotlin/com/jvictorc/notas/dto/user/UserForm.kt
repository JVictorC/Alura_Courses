package com.jvictorc.notas.dto.user

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class UserForm(
    @field:NotEmpty
    @field:Size(max = 20)
    val email: String,
    @field:NotEmpty
    @field:Size(min = 5, max = 20)
    var password: String,
)