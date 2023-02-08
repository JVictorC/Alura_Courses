package com.example.orgs.ui.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.orgs.R
import com.example.orgs.model.Produto

class ListaProdutosAdapter(private var list: MutableList<Produto>, private val context: Context) : RecyclerView.Adapter<ViewHolderListaProdutos>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderListaProdutos {
       val itemProduto = LayoutInflater.from(context).inflate(R.layout.item_produto_lista, parent, false)

        return ViewHolderListaProdutos(itemProduto)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolderListaProdutos, position: Int) {
       holder.vincula(list[position])
    }

    fun insertNewCell(newList: MutableList<Produto>, position: Int) {
        list = newList

        notifyItemInserted(position)
    }
}


class ViewHolderListaProdutos(itemView: View) : ViewHolder(itemView) {
    private val textNome: TextView = itemView.findViewById(R.id.item_produto_lista_nome_receita)
    private val textIngredientes: TextView = itemView.findViewById(R.id.item_produto_lista_ingredientes)
    private val textPreco: TextView = itemView.findViewById(R.id.item_produto_lista_preco)


    fun vincula(produto: Produto) {
        textNome.text = produto.nome
        textIngredientes.text = produto.descricao
        textPreco.text = produto.valor.toString()
    }

}