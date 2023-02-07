package com.example.ceep.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.ceep.model.Nota
import  androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.ceep.R
import java.util.*


class RecyclerNotasAdapter(
    private val context: Context,
    private var notas: MutableList<Nota?>,
) : Adapter<ViewH>() {

    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnItemClickListerner(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewH {
        val view = LayoutInflater.from(context).inflate(R.layout.item_nota, parent, false)

        return ViewH(view)
    }

    override fun getItemCount(): Int {
        return notas.size
    }

    override fun onBindViewHolder(holder: ViewH, position: Int) {
        holder.bind(notas[position])
        holder.setItemListener(notas[position], onItemClickListener, position)
    }


    fun updateRecycle(newList: MutableList<Nota?>, position: Int) {
        notas = newList
        notifyItemChanged(position)
    }

    fun remove(newList: MutableList<Nota?>, position: Int) {
        notas = newList
        notifyItemRemoved(position)
    }


    fun troca(positionInitial: Int, positionFinal: Int) {
        Collections.swap(notas, positionInitial, positionFinal)

       notifyItemMoved(positionInitial, positionFinal)
    }

}

class ViewH(itemView: View) : ViewHolder(itemView) {
    private val textTitle: TextView = itemView.findViewById(R.id.item_nota_titulo)
    private val textSubTitle: TextView = itemView.findViewById(R.id.item_nota_descricao)


    fun bind(nota: Nota?) {
        textTitle.text = nota?.titulo
        textSubTitle.text = nota?.descricao

    }

    fun setItemListener(
        nota: Nota?,
        onItemClickListener: OnItemClickListener,
        position: Int?
    ) {
        itemView.setOnClickListener {
            onItemClickListener.call(nota, position)
        }
    }


}
