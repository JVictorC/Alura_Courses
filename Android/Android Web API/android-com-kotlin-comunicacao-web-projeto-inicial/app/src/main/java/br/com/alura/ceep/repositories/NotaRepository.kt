package br.com.alura.ceep.repositories

import br.com.alura.ceep.database.dao.NotaDao
import br.com.alura.ceep.model.Nota
import br.com.alura.ceep.webclient.NotasWebClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first

class NotaRepository(
    private val dao: NotaDao,
    private val webClient: NotasWebClient
) {

    fun buscaTodas(): Flow<List<Nota>> {
        return dao.buscaTodasNotasAtivas()
    }

    private suspend fun atualizaTodas() {
        webClient.buscaTodas()?.let {

            val notasSincronizadas = it.map { nota ->
                nota.copy(sincronizada = true)
            }

            dao.salvaTodas(notasSincronizadas)
        }
    }

    fun buscaPorId(id: String): Flow<Nota> {
        return dao.buscaPorId(id)
    }

    suspend fun remove(id: String) {
        val isRemoveFromWeb = webClient.remove(id)

        if (isRemoveFromWeb) {
            dao.removeNota(id)
        }

        dao.desativaNota(id)
    }

    suspend fun salva(nota: Nota) {
        val isSafeInWeb = webClient.salva(nota)

        dao.salva(nota.copy(sincronizada = isSafeInWeb))
    }

    suspend fun sincroniza() {
        dao.buscaTodas().first()
            .filter { nota -> !nota.sincronizada }
            .forEach { notaNaoSincronizada ->
                when (notaNaoSincronizada.desativada) {
                    true -> remove(notaNaoSincronizada.id)
                    else -> salva(notaNaoSincronizada)
                }
            }


        atualizaTodas()
    }

}