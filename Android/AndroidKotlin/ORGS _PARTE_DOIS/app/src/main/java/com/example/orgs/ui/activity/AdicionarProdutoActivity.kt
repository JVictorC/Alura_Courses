package com.example.orgs.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import coil.load
import com.example.orgs.R
import com.example.orgs.constantes.Constantes
import com.example.orgs.databinding.ActivityAdicionarProdutoBinding
import com.example.orgs.databinding.FormularioImagemBinding
import com.example.orgs.model.Produto
import com.example.orgs.ui.dialog.FormularioImagemDialog
import loadWithLoading
import java.math.BigDecimal

class AdicionarProdutoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAdicionarProdutoBinding.inflate(layoutInflater)
    }

    private val formularioImagemDialog = FormularioImagemDialog(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val actionBar = supportActionBar

        actionBar?.title = "Cadastrar Produto"

        setListerns()
    }

    private var urlImage: String? = null

    private fun setListerns() {

        binding.adicionarProdutoImagem.setOnClickListener {
            formularioImagemDialog.showDialog(urlImage) {
                urlImage = it
                binding.adicionarProdutoImagem.loadWithLoading(it)
            }
        }

        binding.adicionarProdutoButtonSalvar.setOnClickListener {
            with(binding) {
                val nomeText = adicionarProdutoNome.text.toString()
                val ingredientestext = adicionarProdutoIngredientes.text.toString()
                val valorText = adicionarProdutoValor.text.toString()


                val valorBigDecimal = if (valorText.isBlank()) {
                    BigDecimal.ZERO
                } else {
                    valorText.toBigDecimal()
                }

                val produto = Produto(
                    nomeText,
                    ingredientestext,
                    valorBigDecimal,
                    urlImage,
                )


                Intent().apply {
                    putExtra(Constantes.keyAddNewProduto, produto)
                    setResult(Constantes.keyRequestAddNewProduto, this)
                }

                finish()

            }
        }
    }
}