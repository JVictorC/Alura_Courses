package com.example.orgs.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.orgs.constantes.Constantes
import com.example.orgs.database.AppDataBase
import com.example.orgs.databinding.ActivityListaProdutosBinding
import com.example.orgs.entities.Produto
import com.example.orgs.ui.recyclerView.ListaProdutosAdapter
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class ListaProdutosActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }

    private val adapter by lazy {
        ListaProdutosAdapter(context = this)
    }

    private val db by lazy {
        AppDataBase.getInstance(this)
    }

    private val produtoDao by lazy {
        db.produtoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListners()

        lifecycleScope.launch {
            produtoDao.buscaTodos().collect() { produto ->
                adapter.update(produto.toMutableList())
            }
        }
    }


    private fun setListners() {
        with(binding) {
            adapter.setOnItemClickListerner { produto ->
                Intent(this@ListaProdutosActivity, DetalhesProdutoActivity::class.java).apply {
                    putExtra(Constantes.keyDetailsProduct, produto.id)
                    startActivity(this)
                }
            }

            activityListaProdutosRecyclerView.adapter = adapter

            activityListaProdutosAdicionarNovoProduto.setOnClickListener {
                startActivity(
                    Intent(
                        this@ListaProdutosActivity,
                        FormularioProdutoActivity::class.java
                    )
                )
            }
        }
    }
}