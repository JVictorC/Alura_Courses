package com.example.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.lifecycleScope
import com.example.orgs.constantes.Constantes
import com.example.orgs.database.AppDataBase
import com.example.orgs.databinding.ActivityDetalhesProdutoBinding
import com.example.orgs.entities.Produto
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import loadWithLoading


class DetalhesProdutoActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityDetalhesProdutoBinding.inflate(layoutInflater)
    }

    private val dao by lazy {
        AppDataBase.getInstance(this).produtoDao()
    }

    private lateinit var produto: Produto
    private var produtoId: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initProductIdExtra()

        lifecycleScope.launch {
            dao.buscaPeloId(produtoId).collect { productById ->
                productById?.let {
                    produto = it
                    supportActionBar?.title = "Detalhes ${produto.nome}"
                    initPage()
                } ?: finish()
            }

        }
    }

    private fun initProductIdExtra() {
        with(intent) {
            if (hasExtra(Constantes.keyDetailsProduct)) {
                produtoId = getLongExtra(Constantes.keyDetailsProduct, 0L)
            }
        }
    }

    private fun initPage() {
        if (::produto.isInitialized) {
            with(binding) {
                detalhesProdutoDescricao.text = produto.descricao
                detalhesProdutoTitulo.text = produto.nome
                detalhesProdutoValor.text = produto.getFormatedPtBrValor()

                if (produto.imagemUrl?.isBlank() == true || produto.imagemUrl == null) {
                    val id = resources.getIdentifier(
                        "imagem_padrao", "drawable",
                        packageName
                    )

                    detalhesProdutoImagem.setImageDrawable(
                        AppCompatResources.getDrawable(this@DetalhesProdutoActivity, id)
                    )
                } else {
                    detalhesProdutoImagem.loadWithLoading(produto.imagemUrl)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.example.orgs.R.menu.menu_detalhes_produto, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (::produto.isInitialized) {
            when (item.itemId) {
                com.example.orgs.R.id.menu_detalhes_produto_editar -> editProduct()
                com.example.orgs.R.id.menu_detalhes_produto_excluir -> deleteProduct()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteProduct() {
        lifecycleScope.launch {
            dao.delete(produto)
            finish()
        }
    }

    private fun editProduct() {
        Intent(this, FormularioProdutoActivity::class.java).apply {
            putExtra(Constantes.keyUpdateProduto, produtoId)

            startActivity(this)
        }
    }
}