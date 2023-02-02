package com.example.agenda.model.aluno

class Aluno() {
    var nome: String = ""
        private set
    var telefone: String = ""
        private set
    var email: String = ""
        private set

    constructor(nome: String, telefone: String, email: String) : this() {
        this.nome = nome
        this.telefone = telefone
        this.email = email
    }

    override fun toString(): String {
        return nome
    }


}
