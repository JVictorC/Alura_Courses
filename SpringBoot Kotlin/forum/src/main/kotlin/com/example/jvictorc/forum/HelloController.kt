package com.example.jvictorc.forum.controller

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/hello")
class HelloController {
    @GetMapping()
    fun hello() : String {
        return "Hello World teste"
    }
}