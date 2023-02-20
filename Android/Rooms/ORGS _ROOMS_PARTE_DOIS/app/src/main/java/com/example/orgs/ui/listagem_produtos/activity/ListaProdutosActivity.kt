package com.example.orgs.ui.listagem_produtos.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.example.orgs.R
import com.example.orgs.constantes.Constantes
import com.example.orgs.database.AppDataBase
import com.example.orgs.databinding.ActivityListaProdutosBinding
import com.example.orgs.entities.Usuario
import com.example.orgs.extensios.vaiPara
import com.example.orgs.preferences.dataStore
import com.example.orgs.ui.autenticacao.activity.LoginActivity
import com.example.orgs.ui.listagem_produtos.recyclerView.ListaProdutosAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlin.reflect.typeOf

class ListaProdutosActivity : UsuarioBaseActivity() {
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

        lifecycleScope.launch {
            usuarioLogged.filterNotNull().collect() {
                buscaProdutos(it.id)
            }
        }
    }

    private suspend fun buscaProdutos(userId: String) {
        produtoDao.buscaTodosDoUsuario(userId).collect() {
            adapter.update(it.toMutableList())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lista_produtos, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_lista_produto_sair_app -> logout()
        }

        return super.onOptionsItemSelected(item)
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