package com.example.orgs.model

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*


class Produto(
    val nome: String,
    val descricao: String,
    val valor: BigDecimal,
    val imagemUrl: String? = null,
) : java.io.Serializable {


    fun getFormatedPtBrValor() : String {
        val formatPtBr = NumberFormat.getCurrencyInstance(Locale("pt", "br"))

       return  formatPtBr.format(valor)
    }

}