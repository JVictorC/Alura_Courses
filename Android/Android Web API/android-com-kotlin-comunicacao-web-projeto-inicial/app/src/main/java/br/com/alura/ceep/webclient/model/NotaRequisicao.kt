package br.com.alura.ceep.webclient.model

import br.com.alura.ceep.model.Nota
import java.util.*

class NotaRequisicao(
    private val titulo: String?,
    private val descricao: String?,
    private val imagem: String?,
) {
    val nota: Nota
        get() = Nota(
            titulo = titulo ?: "",
            descricao = descricao ?: "",
            imagem = imagem ?: "",
        )


    companion object {
        fun fromNota(nota: Nota) : NotaRequisicao {
            return NotaRequisicao(
                nota.titulo,
                nota.descricao,
                nota.imagem
            )
        }
    }
}