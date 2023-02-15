package com.example.orgs.ui.listagem_produtos.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.orgs.databinding.FormularioImagemBinding
import loadWithLoading

class FormularioImagemDialog(private val context: Context) {
    fun showDialog(
        urlAlredyInsert: String? = null,
        onLoadImageUrl: (
            url: String?
        ) -> Unit
    ) {
        val binding = FormularioImagemBinding.inflate(LayoutInflater.from(context))

        with(binding) {
            urlAlredyInsert?.let {
               formularioImagem.loadWithLoading(it)
               formularioImagemUrlImagem.setText(it)
            }

           formularioImagemButtonCarregar.setOnClickListener {
                val url = formularioImagemUrlImagem.text.toString()

               formularioImagem.loadWithLoading(url)
            }

            AlertDialog.Builder(context)
                .setView(binding?.root)
                .setPositiveButton("Confirmar") { _, _ ->
                    val url = formularioImagemUrlImagem.text.toString()

                    onLoadImageUrl(url)
                }
                .setNegativeButton("Cancelar") { _, _ -> }
                .show()
        }
    }
}