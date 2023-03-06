package com.jvictorc.forum.controllers

import com.jvictorc.forum.dto.usuario.UsuarioForm
import com.jvictorc.forum.model.Usuario
import com.jvictorc.forum.services.UsuariosService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/usuarios")
class UsuariosController(
    private val services: UsuariosService
) {

    @GetMapping
    fun getAll() : List<Usuario> {
        return services.listarTodos()
    }


    @PostMapping
    fun createNewUser(@Valid @RequestBody newUser: UsuarioForm) : Usuario {
        return services.createNewUser(newUser)
    }


    @DeleteMapping("/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteAll() {
        services.deleteAll()
    }

}