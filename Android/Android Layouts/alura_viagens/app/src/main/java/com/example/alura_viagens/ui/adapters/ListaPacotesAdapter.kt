package com.example.alura_viagens.ui.adapters

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.example.alura_viagens.R
import com.example.alura_viagens.model.PacoteModel
import java.text.DecimalFormat
import java.util.Locale

class ListaPacotesAdapter(private val pacotes: MutableList<PacoteModel>, private val context: Context): BaseAdapter() {
    override fun getCount(): Int {
        return pacotes.size
    }

    override fun getItem(position: Int): Any {
        return pacotes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
       val itemPacote = LayoutInflater.from(context)?.inflate(R.layout.item_pacote, parent, false) as View

        val pacote = pacotes[position]

        criarImagemCidade(pacote, itemPacote)

        criarTextNomeCidade(itemPacote, pacote)

        criarQuantidadeDias(itemPacote, pacote)

        criarPreco(itemPacote, pacote)

        return itemPacote
    }

    private fun criarPreco(
        itemPacote: View,
        pacote: PacoteModel
    ) {
        val preco: TextView = itemPacote.findViewById(R.id.item_pacote_preco)


        val formartBR = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))

        preco.text = formartBR.format(pacote.preco)
    }

    private fun criarQuantidadeDias(
        itemPacote: View,
        pacote: PacoteModel
    ) {
        val quantidadeDias: TextView = itemPacote.findViewById(R.id.item_pacote_dias)
        if (pacote.dias > 1) quantidadeDias.text = "${pacote.dias} Dias"
        else quantidadeDias.text = "${pacote.dias} Dia"
    }

    private fun criarImagemCidade(
        pacote: PacoteModel,
        itemPacote: View
    ) {
        val resources = context.resources

        val idImage = resources.getIdentifier(
            pacote.image,
            "drawable",
            context.packageName
        )

        val drawable = ResourcesCompat.getDrawable(resources, idImage, resources.newTheme())
        val imagemPacote: ImageView = itemPacote.findViewById(R.id.item_pacote_image_view)
        imagemPacote.setImageDrawable(drawable)
    }

    private fun criarTextNomeCidade(
        itemPacote: View,
        pacote: PacoteModel
    ) {
        val nomeCidade: TextView = itemPacote.findViewById(R.id.item_pacote_nome_cidade)
        nomeCidade.text = pacote.local
    }
}
