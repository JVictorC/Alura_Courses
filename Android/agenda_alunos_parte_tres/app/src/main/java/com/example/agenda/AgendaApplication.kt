package com.example.agenda

import android.app.Application
import com.example.agenda.dao.AlunoDAO
import com.example.agenda.model.Aluno

class AgendaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        criaAlunosTestes()
    }


    private fun criaAlunosTestes() {
        val dao = AlunoDAO()

        dao.salva(Aluno("Fran", "1122223333", "fran@gmail.com"))
        dao.salva(Aluno("Teste", "1122223333", "fran@gmail.com"))
    }
}