package com.example.orgs.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.orgs.Constantes
import com.example.orgs.databinding.ActivityAdicionarProdutoBinding
import com.example.orgs.model.Produto
import java.math.BigDecimal

class AdicionarProdutoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityAdicionarProdutoBinding.inflate(layoutInflater)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListerns()
    }

    private fun setListerns() {

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
                    valorBigDecimal
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