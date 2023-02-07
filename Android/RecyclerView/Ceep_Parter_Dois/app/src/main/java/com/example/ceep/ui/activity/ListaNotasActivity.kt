package com.example.ceep.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ceep.dao.NotaDAO
import com.example.ceep.databinding.ActivityListaNotasBinding
import com.example.ceep.model.Nota
import com.example.ceep.ui.Constantes
import com.example.ceep.ui.adapters.OnItemClickListener
import com.example.ceep.ui.adapters.RecyclerNotasAdapter
import com.example.ceep.ui.adapters.callback.NotaItemTouchHelpCallBack

class ListaNotasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListaNotasBinding
    private lateinit var adapter: RecyclerNotasAdapter
    private val dao = NotaDAO()


    private val retorno =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            result.data?.let { intent ->
                if (result.resultCode == Constantes.keyNotaRequestEditar) {
                    if (intent.hasExtra(Constantes.keyNotaEditar)) {

                        val nota = intent.getSerializableExtra(Constantes.keyNotaEditar) as Nota
                        val index = intent.getIntExtra(Constantes.keyIndexNotaEditar, 0)

                        dao.altera(nota, index)
                        adapter.updateRecycle(dao.todos(), index)
                    }
                }

                if (result.resultCode == Constantes.keyNotaRequestSalvar) {
                    if (intent.hasExtra(Constantes.keyNotaEditar)) {

                        val nota = intent.getSerializableExtra(Constantes.keyNotaEditar) as Nota

                        dao.insere(nota)
                        adapter.updateRecycle(dao.todos(), dao.todos().lastIndex)
                    }
                }

            }

        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListaNotasBinding.inflate(layoutInflater)

        setContentView(binding.root)


        for (i in 0..2) {
            dao.insere(Nota("Nata $i", "Descricao $i"))
        }

        configRecyclerView()
        setListener()
    }

    private fun setListener() {
        binding.listaNotasInsereNota.setOnClickListener {
            Intent(this, AdicionarNotaActivity::class.java).apply {
                retorno.launch(this)
            }
        }
    }

    private fun configRecyclerView() {
        adapter = RecyclerNotasAdapter(this, dao.todos())

        binding.listaNotasRecyclerview.adapter = adapter
        binding.listaNotasRecyclerview.layoutManager = LinearLayoutManager(this)


        val itemTouchHelper = ItemTouchHelper(NotaItemTouchHelpCallBack(adapter))

        itemTouchHelper.attachToRecyclerView(binding.listaNotasRecyclerview)

        adapter.setOnItemClickListerner { nota, index ->
            Intent(this, AdicionarNotaActivity::class.java).apply {
                putExtra(Constantes.keyNotaEditar, nota)
                putExtra(Constantes.keyIndexNotaEditar, index)

                retorno.launch(this)
            }


        }

    }
}