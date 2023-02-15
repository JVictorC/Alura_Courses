package com.example.orgs.ui.listagem_produtos.helpers

import com.example.orgs.databinding.ActivityFormularioProdutoBinding

class ValidatesFormularioProdutoHelper(private val binding: ActivityFormularioProdutoBinding) {
    fun validates() : Boolean {
        val validates = listOf<Boolean>(
            validateNomeProduto(),
            validateValorProduto()
        )

        return  validates.all { it }
    }

    private fun validateNomeProduto() : Boolean {
        val nomeProduto = binding.formularioProdutoNome.text.toString()
        if(nomeProduto.isBlank()) {
            binding.formularioProdutoTextinputlayoutNome.error = "Campo Nome Não pode ser vazio"
            return false
        }

        binding.formularioProdutoTextinputlayoutNome.error = ""

        return true
    }

    private fun validateValorProduto() : Boolean {
        val valorProduto = binding.formularioProdutoValor.text.toString()
        if(valorProduto.isBlank()) {
            binding.formularioProdutoTextinputlayoutValor.error = "Campo Valor Não pode ser vazio"
            return false
        }

        binding.formularioProdutoTextinputlayoutValor.error = ""
        return true
    }
}