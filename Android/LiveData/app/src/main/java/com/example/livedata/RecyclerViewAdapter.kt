package com.example.livedata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class RecyclerViewAdapter(private var listNotas: MutableList<Nota>?, private val context: Context) : Adapter<ViewHolderRecycler>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRecycler {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_lista, parent, false)


        return ViewHolderRecycler(itemView)
    }

    override fun getItemCount(): Int {
        return  listNotas?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolderRecycler, position: Int) {
        holder.bind(listNotas?.get(position))
    }


    fun update(newList: MutableList<Nota>?) {
        listNotas = newList

        notifyDataSetChanged()
    }
}


class ViewHolderRecycler(itemView: View) : ViewHolder(itemView) {
    val textTitle: TextView = itemView.findViewById(R.id.item_lista_title)
    val textSubTitle: TextView = itemView.findViewById(R.id.item_lista_SubTitle)

    fun bind(nota: Nota?) {
        textTitle.text = nota?.titulo
        textSubTitle.text = nota?.subTitle
    }
}