package com.example.orgs.ui.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.orgs.databinding.ItemProdutoListaBinding
import com.example.orgs.entities.Produto
import loadWithLoading



class ListaProdutosAdapter(private var list: MutableList<Produto> = mutableListOf(), private val context: Context) : RecyclerView.Adapter<ViewHolderListaProdutos>() {

    private var onItemClick: ((Produto) -> Unit)? = null

    fun setOnItemClickListerner(onItemClick: ((Produto) -> Unit)? = null) {
        this.onItemClick = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderListaProdutos {
        val inflater = LayoutInflater.from(context)
        val itemProduto = ItemProdutoListaBinding.inflate(inflater, parent, false)

        return ViewHolderListaProdutos(itemProduto, onItemClick)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolderListaProdutos, position: Int) {
        holder.vincula(list[position])
    }

    fun update(newList: MutableList<Produto>) {
        list = newList

        notifyDataSetChanged()
    }


    fun insertNewCell(newList: MutableList<Produto>, position: Int) {
        list = newList

        notifyItemInserted(position)
    }
}


class ViewHolderListaProdutos(itemView: ItemProdutoListaBinding, private val onItemClick: ((Produto) -> Unit)? = null) : ViewHolder(itemView.root) {
    private val textNome = itemView.itemProdutoListaNomeReceita
    private val textIngredientes = itemView.itemProdutoListaIngredientes
    private val textPreco = itemView.itemProdutoListaPreco
    private val imageView = itemView.itemProdutoListaImagem


    fun vincula(produto: Produto) {
        itemView.setOnClickListener {
            onItemClick?.invoke(produto)
        }


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