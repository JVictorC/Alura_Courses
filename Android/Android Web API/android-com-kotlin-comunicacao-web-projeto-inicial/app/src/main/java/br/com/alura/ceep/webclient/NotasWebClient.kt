package br.com.alura.ceep.webclient

import android.util.Log
import br.com.alura.ceep.model.Nota
import br.com.alura.ceep.webclient.model.NotaRequisicao

class NotasWebClient {
    private val servicesNotas by lazy {
        InitRetroFit().notasServices
    }


    suspend fun remove(id: String) : Boolean {
        return try {
            val response = servicesNotas.removeNota(id)

            response.isSuccessful
        } catch (e: Exception) {
            Log.e("REMOVE_NOTA", e.toString())
            false
        }
    }

    suspend fun salva(nota: Nota): Boolean {
        return try {
            val response = servicesNotas.salvaNota(nota.id, NotaRequisicao.fromNota(nota))

            response.isSuccessful
        } catch (e: Exception) {
            Log.e("SALVAR_NOTA", e.toString())
            false
        }
    }


    suspend fun buscaTodas(): List<Nota>? {
        return try {
            servicesNotas.getAllNotas().map { it.nota }
        } catch (e: Exception) {
            null
        }
    }
}