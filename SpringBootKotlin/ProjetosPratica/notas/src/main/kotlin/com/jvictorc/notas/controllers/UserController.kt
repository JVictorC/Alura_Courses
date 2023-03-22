package com.jvictorc.notas.controllers

import com.jvictorc.notas.dto.user.StatusUser
import com.jvictorc.notas.dto.user.TokenJwt
import com.jvictorc.notas.dto.user.UserForm
import com.jvictorc.notas.dto.user.UserView
import com.jvictorc.notas.services.UserServices
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    val services: UserServices,
) {
    @PostMapping("/create-account")
    @Transactional
    fun createCount(
        @RequestBody @Valid newUser: UserForm
    ) : UserView {
        return services.createNewUser(newUser)
    }

    @GetMapping("/validate-user")
    fun validateUser(
        @Valid @RequestBody userForm: UserForm
    ) : StatusUser {
        return StatusUser(services.validatedUser(userForm))
    }

}