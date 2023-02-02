package com.example.ceep.ui.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.ceep.model.Nota
import  androidx.recyclerview.widget.RecyclerView.ViewHolder


class RecyclerNotasAdapter(private val context: Context, private val notas: MutableList<Nota?>) : Adapter<ViewH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewH {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return notas.size
    }

    override fun onBindViewHolder(holder: ViewH, position: Int) {
        TODO("Not yet implemented")
    }

}


class ViewH(view: View) : ViewHolder(view) {

}
