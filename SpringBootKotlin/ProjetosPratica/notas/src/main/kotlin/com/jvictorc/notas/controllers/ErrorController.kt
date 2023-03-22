package com.jvictorc.notas.controllers

import com.jvictorc.notas.dto.error.ErrorView
import com.jvictorc.notas.exeption.NotAuthorization
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class ErrorController {

    @GetMapping("/403-token-expired")
    fun notAuthorization(
    ) : ErrorView {
        return ErrorView(
            error = HttpStatus.valueOf(403).name,
            status = 403,
            message = "Token Expired",
            path = "/403"
        )
    }

}