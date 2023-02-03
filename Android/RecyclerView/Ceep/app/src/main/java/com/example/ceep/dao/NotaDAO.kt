package com.example.ceep.dao

import com.example.ceep.model.Nota
import java.util.*

class NotaDAO {
    companion object {
        private val notas: MutableList<Nota?> = mutableListOf()
    }

    fun todos(): MutableList<Nota?> {
        return notas.toMutableList()
    }

    fun insere(nota: Nota) {
        NotaDAO.notas.add(nota)
    }

    fun altera(posicao: Int, nota: Nota?) {
        notas[posicao] = nota
    }

    fun remove(posicao: Int) {
        notas.removeAt(posicao)
    }

    fun troca(posicaoInicio: Int, posicaoFim: Int) {
        Collections.swap(notas, posicaoInicio, posicaoFim)
    }

    fun removeTodos() {
        notas.clear()
    }
}