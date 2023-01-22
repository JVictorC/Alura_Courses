package com.example.agenda.dao

import com.example.agenda.model.Aluno


class AlunoDAO {
    companion object {
        private var alunos: MutableList<Aluno> = mutableListOf()
    }

    fun salva(aluno: Aluno) {
        alunos.add(aluno)
    }

    fun editAluno(aluno: Aluno) {
        val oldIndex = alunos.indexOfFirst { it ->  it.id == aluno.id }

        alunos[oldIndex] = aluno
    }

    fun removeAluno(aluno: Aluno) {
        alunos.remove(aluno)

        println(alunos)
    }

    fun todos(): List<Aluno> = alunos.toMutableList()
}
