package com.example.orgs.dao

import com.example.orgs.model.Produto

class ProdutoDao {

    companion object {
        private val produtos: MutableList<Produto> = mutableListOf()
    }

    fun adicionar(produto: Produto) {
        produtos.add(produto)
    }
    fun todos() : MutableList<Produto> = produtos.toMutableList()
}