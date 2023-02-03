package com.example.ceep.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ceep.dao.NotaDAO
import com.example.ceep.databinding.ActivityListaNotasBinding
import com.example.ceep.model.Nota
import com.example.ceep.ui.adapters.RecyclerNotasAdapter

class ListaNotasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListaNotasBinding
    private lateinit var adapter: RecyclerNotasAdapter
    private val dao = NotaDAO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListaNotasBinding.inflate(layoutInflater)

        setContentView(binding.root)


        for (i in 0..2) {
            dao.insere(Nota("Nata $i", "Descricao $i"))
        }

        configRecyclerView()
        setListeners()
    }

    override fun onResume() {
        super.onResume()
        adapter.updateRecycle(dao.todos(), dao.todos().lastIndex)
    }

    private fun setListeners() {
        binding.listaNotasInsereNota.setOnClickListener {
            startActivity(Intent(this, AdicionarNotaActivity::class.java))
        }
    }

    private fun configRecyclerView() {
        adapter = RecyclerNotasAdapter(this, dao.todos())
        binding.listaNotasRecyclerview.adapter = adapter
        binding.listaNotasRecyclerview.layoutManager = LinearLayoutManager(this)
    }
}