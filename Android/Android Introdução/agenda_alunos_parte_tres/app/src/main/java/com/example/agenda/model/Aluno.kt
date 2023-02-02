package com.example.agenda.model

import java.io.Serializable
import kotlin.random.Random


class Aluno(nome: String, telefone: String, email: String) : Serializable {
    val id: Int =  Random.nextInt(0, 10000)

    var nome: String = nome
        private set
    var telefone: String = telefone
        private set
    var email: String = email
        private set

    override fun toString(): String {
        return nome
    }

    fun editarAluno(nome: String, telefone:String, email:String) {
        this.nome = nome
        this.telefone = telefone
        this.email = email
    }
}
