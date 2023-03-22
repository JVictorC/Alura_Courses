package com.jvictorc.notas.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class HellloController {

    @GetMapping
    fun test(
        @RequestHeader("Authorization") token: String
    ) : String {
        return token
    }

}