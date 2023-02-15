package com.example.orgs.ui.listagem_produtos.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.orgs.constantes.Constantes
import com.example.orgs.database.AppDataBase
import com.example.orgs.databinding.ActivityListaProdutosBinding
import com.example.orgs.ui.listagem_produtos.recyclerView.ListaProdutosAdapter

class ListaProdutosActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }

    private val adapter by lazy {
        ListaProdutosAdapter(context = this)
    }

    private val produtoDao by lazy {
        AppDataBase.instancia(this).produtoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListners()
    }

    override fun onResume() {
        super.onResume()

        adapter.update(produtoDao.buscaTodos().toMutableList())
    }

    private fun setListners() {
        with(binding) {

            adapter.setOnItemClickListerner {produto ->
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