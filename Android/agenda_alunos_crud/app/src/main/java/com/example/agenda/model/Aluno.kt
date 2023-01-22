package com.example.agenda.model

import java.io.Serializable
import kotlin.random.Random


class Aluno : Serializable {
    val id: Int =  Random.nextInt(0, 10000);

    var nome: String = ""
        private set
    var telefone: String = ""
        private set
    var email: String = ""
        private set

    constructor(nome: String, telefone: String, email: String) {
        this.nome = nome
        this.telefone = telefone
        this.email = email
    }

    override fun toString(): String {
        return nome
    }

    fun editarAluno(nome: String, telefone:String, email:String) {
        this.nome = nome
        this.telefone = telefone
        this.email = email
    }
}
