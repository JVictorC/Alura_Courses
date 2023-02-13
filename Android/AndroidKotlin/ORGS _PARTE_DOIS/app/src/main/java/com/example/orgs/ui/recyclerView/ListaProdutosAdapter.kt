package com.example.orgs.ui.recyclerView

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.orgs.databinding.ItemProdutoListaBinding
import com.example.orgs.model.Produto
import loadWithLoading
import java.util.*

class ListaProdutosAdapter(private var list: MutableList<Produto>, private val context: Context) :
    RecyclerView.Adapter<ViewHolderListaProdutos>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderListaProdutos {
        val inflater = LayoutInflater.from(context)
        val itemProduto = ItemProdutoListaBinding.inflate(inflater, parent, false)

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


class ViewHolderListaProdutos(itemView: ItemProdutoListaBinding) : ViewHolder(itemView.root) {
    private val textNome = itemView.itemProdutoListaNomeReceita
    private val textIngredientes = itemView.itemProdutoListaIngredientes
    private val textPreco = itemView.itemProdutoListaPreco
    private val imageView = itemView.itemProdutoListaImagem


    fun vincula(produto: Produto) {
        textNome.text = produto.nome
        textIngredientes.text = produto.descricao
        textPreco.text =  produto.getFormatedPtBrValor()

       val visibility = if(produto.imagemUrl != null) {
            View.VISIBLE
        } else {
            View.GONE
        }

        imageView.visibility = visibility

        imageView.loadWithLoading(produto.imagemUrl)
    }

}