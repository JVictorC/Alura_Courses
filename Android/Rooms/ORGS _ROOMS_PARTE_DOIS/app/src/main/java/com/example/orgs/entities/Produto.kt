package com.example.orgs.entities

import android.R
import android.os.Parcelable
import androidx.appcompat.content.res.AppCompatResources
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*


@Entity
@Parcelize
class Produto(
    @PrimaryKey(true)
    val id: Long = 0L,
    val nome: String,
    val descricao: String,
    val valor: BigDecimal,
    val imagemUrl: String? = null,
    val usuarioId: String? = null,
) : Parcelable {
    fun getFormatedPtBrValor() : String {
        val formatPtBr = NumberFormat.getCurrencyInstance(Locale("pt", "br"))

       return  formatPtBr.format(valor)
    }

    fun update(
        nome: String,
        descricao: String,
        valor: BigDecimal,
        imagemUrl: String?,
        userId: String?,
    ) : Produto {
        return Produto(
            id,
            nome,
            descricao,
            valor,
            imagemUrl,
            userId,
        )
    }
}