package com.example.orgs.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.orgs.constantes.Constantes
import com.example.orgs.database.AppDataBase
import com.example.orgs.databinding.ActivityFormularioProdutoBinding
import com.example.orgs.entities.Produto
import com.example.orgs.ui.dialog.FormularioImagemDialog
import com.example.orgs.ui.helpers.ValidatesFormularioProdutoHelper
import kotlinx.coroutines.*
import loadWithLoading
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }

    private val dao by lazy {
        AppDataBase.getInstance(this).produtoDao()
    }

    private var produto: Produto = Produto(
        nome = "",
        descricao = "",
        valor = BigDecimal("0")
    )

    private var produtoId: Long = 0L

    private var urlImage: String? = null

    private val formularioImagemDialog = FormularioImagemDialog(this)

    private val validatesFormularioProdutoHelper by lazy {
        ValidatesFormularioProdutoHelper(binding)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title = "Cadastrar Produto"

        initProductIdExtra()

        setListerns()

        if (produtoId != 0L) {
            initProductFromDataBase()
        }
    }

    private fun initProductFromDataBase() {
        binding.adicionarProdutoButtonSalvar.text = "Editar"

        supportActionBar?.title = "Editar Produto"

        lifecycleScope.launch() {
            dao.buscaPeloId(produtoId).collect { productById ->
                productById?.let {
                    produto = it
                    setFields()
                } ?: finish()
            }
        }
    }

    private fun initProductIdExtra() {
        intent.apply {
            if (hasExtra(Constantes.keyUpdateProduto)) {
                getLongExtra(Constantes.keyUpdateProduto, 0L).apply {
                    produtoId = this
                }
            }
        }

    }

    private fun setFields() {
        produto.let { produto ->
            with(binding) {
                formularioProdutoNome.setText(produto.nome)
                formularioProdutoIngredientes.setText(produto.descricao)
                formularioProdutoValor.setText(produto.valor.toString())
                adicionarProdutoImagem.loadWithLoading(produto.imagemUrl)

                urlImage = produto.imagemUrl
            }
        }
    }

    private fun setListerns() {
        binding.adicionarProdutoImagem.setOnClickListener {
            formularioImagemDialog.showDialog(urlImage) {
                urlImage = it
                binding.adicionarProdutoImagem.loadWithLoading(it)
            }
        }

        binding.adicionarProdutoButtonSalvar.setOnClickListener {
            with(binding) {


                val nomeText = formularioProdutoNome.text.toString()
                val ingredientestext = formularioProdutoIngredientes.text.toString()
                val valorText = formularioProdutoValor.text.toString()
                val valorBigDecimal = if (valorText.isBlank()) {
                    BigDecimal.ZERO
                } else {
                    valorText.toBigDecimal()
                }

                if (validatesFormularioProdutoHelper.validates()) {
                    produto.update(nomeText, ingredientestext, valorBigDecimal, urlImage).let {
                        lifecycleScope.launch() {
                            dao.salva(it)
                            finish()
                        }
                    }
                }
            }
        }
    }
}