package com.example.alura_viagens.model

import java.math.BigDecimal

class PacoteModel(local: String, image: String, dias: Int, preco: BigDecimal) {
    var local: String = local
        private set
    var image: String = image
        private set
    var dias: Int = dias
        private set
    var preco: BigDecimal = preco
        private set
}