package com.example.agenda.dao

import com.example.agenda.model.aluno.Aluno


class AlunoDAO {
    companion object {
        private var alunos: MutableList<Aluno> = mutableListOf()
    }


    fun salva(aluno: Aluno) {
        alunos.add(aluno)
    }

    fun todos(): List<Aluno> = alunos

}
