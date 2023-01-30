package com.example.alura_viagens.model

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class PacoteModel(local: String, image: String, dias: Int, preco: BigDecimal) : java.io.Serializable {
    var local: String = local
        private set
    var image: String = image
        private set
    var dias: Int = dias
        private set
    var preco: BigDecimal = preco
        private set
    var diasString: String = ""
        get() {
            if (dias > 1) return "$dias Dias"
            return "$dias Dia"
        }
    private set


    fun createPrecoComBRFormart() : String {
        val formartBR = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))

        return formartBR.format(preco)
    }

    fun createDrawableImage(context: Context) : Drawable? {
        val resources = context.resources

        val idImage = resources.getIdentifier(
            image,
            "drawable",
            context.packageName
        )

        return ResourcesCompat.getDrawable(resources, idImage, resources.newTheme())
    }

    fun createDataFormatada(): String {
        val dataIda = Calendar.getInstance()
        val dataVolta = Calendar.getInstance()
        dataVolta.add(Calendar.DATE, dias)

        val formatBR = SimpleDateFormat("dd/MM", Locale("pt", "br"))

        val dataFormatadaIda = formatBR.format(dataIda.time)
        val dataFormatadaVolta = formatBR.format(dataVolta.time)

        return "$dataFormatadaIda - $dataFormatadaVolta de ${dataVolta[Calendar.YEAR]}"
    }
}