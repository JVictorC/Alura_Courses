package com.example.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.livedata.databinding.ListaMainBinding

class ListaActivity : AppCompatActivity() {
    private lateinit var binding: ListaMainBinding
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var model: NotaModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ListaMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this)[NotaModel::class.java]


        model.listaNotas.observe(this) {
            adapter.update(it)
        }




        binding.listaActivityButton.setOnClickListener {
            model.inserirNota(Nota("2", "1"))
        }

        configRecyclerView()
    }

    private fun configRecyclerView() {
        val recyclerView = binding.listaActivityRecycler


        adapter = RecyclerViewAdapter(model.listaNotas.value , this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}