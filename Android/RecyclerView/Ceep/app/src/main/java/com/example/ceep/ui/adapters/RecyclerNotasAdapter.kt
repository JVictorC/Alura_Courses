package com.example.ceep.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.ceep.model.Nota
import  androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.ceep.R
import org.w3c.dom.Text


class RecyclerNotasAdapter(private val context: Context, private var notas: MutableList<Nota?>) : Adapter<ViewH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewH {
        val view = LayoutInflater.from(context).inflate(R.layout.item_nota, parent, false)

        return ViewH(view)
    }

    override fun getItemCount(): Int {
        return notas.size
    }

    override fun onBindViewHolder(holder: ViewH, position: Int) {
       holder.bind(notas[position])
    }



    fun updateRecycle(newList: MutableList<Nota?>, positionAtUpdate: Int) {
        notas = newList
        notifyItemChanged(positionAtUpdate)
    }

}


class ViewH(view: View) : ViewHolder(view) {
    private val textTitle: TextView = view.findViewById(R.id.item_nota_titulo)
    private val textSubTitle: TextView = view.findViewById(R.id.item_nota_descricao)


    fun bind(nota: Nota?) {
        textTitle.text = nota?.titulo
        textSubTitle.text = nota?.descricao
    }

}
