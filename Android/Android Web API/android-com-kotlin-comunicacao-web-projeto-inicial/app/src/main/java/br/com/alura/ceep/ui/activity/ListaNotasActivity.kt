package br.com.alura.ceep.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import br.com.alura.ceep.database.AppDatabase
import br.com.alura.ceep.databinding.ActivityListaNotasBinding
import br.com.alura.ceep.extensions.vaiPara
import br.com.alura.ceep.repositories.NotaRepository
import br.com.alura.ceep.ui.recyclerview.adapter.ListaNotasAdapter
import br.com.alura.ceep.webclient.InitRetroFit
import br.com.alura.ceep.webclient.NotasWebClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class ListaNotasActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityListaNotasBinding.inflate(layoutInflater)
    }
    private val adapter by lazy {
        ListaNotasAdapter(this)
    }
    private val repository by lazy {
        NotaRepository(
            AppDatabase.instancia(this).notaDao(),
            NotasWebClient(),
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraFab()
        configuraRecyclerView()
        configuraReload()
        lifecycleScope.launch {
            launch(Dispatchers.IO) {
                repository.sincroniza()
            }
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                buscaNotas()
            }
        }
    }

    private fun configuraReload() {
        with(binding) {
            pullToRefresh.setOnRefreshListener {

                lifecycleScope.launch {
                    launch(Dispatchers.IO) {
                        repository.sincroniza()
                    }

                    pullToRefresh.isRefreshing = false;
                }

            }
        }
    }

    private fun configuraFab() {
        binding.activityListaNotasFab.setOnClickListener {
            Intent(this, FormNotaActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    private fun configuraRecyclerView() {
        binding.activityListaNotasRecyclerview.adapter = adapter
        adapter.quandoClicaNoItem = { nota ->
            vaiPara(FormNotaActivity::class.java) {
                putExtra(NOTA_ID, nota.id)

            }
        }
    }

    private suspend fun buscaNotas() {
        repository.buscaTodas()
            .collect { notasEncontradas ->
                binding.activityListaNotasMensagemSemNotas.visibility =
                    if (notasEncontradas.isEmpty()) {
                        binding.activityListaNotasRecyclerview.visibility = GONE
                        VISIBLE
                    } else {
                        binding.activityListaNotasRecyclerview.visibility = VISIBLE
                        adapter.atualiza(notasEncontradas)
                        GONE
                    }
            }
    }
}