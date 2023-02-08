package com.example.orgs.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orgs.Constantes
import com.example.orgs.dao.ProdutoDao
import com.example.orgs.databinding.ActivityListaProdutosBinding
import com.example.orgs.model.Produto
import com.example.orgs.ui.recyclerView.ListaProdutosAdapter

class ListaProdutosActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }
    private val adapter by lazy {
        ListaProdutosAdapter(mutableListOf(), this)
    }

    private val resultFromAdicionarProduto = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if(it.resultCode == Constantes.keyRequestAddNewProduto) {
            it.data?.let {intent ->
                if(intent.hasExtra(Constantes.keyAddNewProduto)) {
                    val produto = intent.getSerializableExtra(Constantes.keyAddNewProduto) as Produto
                    produtoDao.adicionar(produto)

                    adapter.insertNewCell(produtoDao.todos(), produtoDao.todos().lastIndex)
                }
            }
        }
    }

    private val produtoDao = ProdutoDao()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListners()
    }

    private fun setListners() {
        with(binding) {
            activityListaProdutosRecyclerView.adapter = adapter


            activityListaProdutosAdicionarNovoProduto.setOnClickListener {
                resultFromAdicionarProduto.launch(Intent(this@ListaProdutosActivity, AdicionarProdutoActivity::class.java))
            }
        }
    }
}