package com.example.alura_viagens.dao

import com.example.alura_viagens.model.PacoteModel
import java.math.BigDecimal


class PacoteDAO {
    fun lista(): MutableList<PacoteModel> {
        return mutableListOf(
            PacoteModel("São Paulo", "sao_paulo_sp", 2, BigDecimal(243.99)),
            PacoteModel("Belo Horizonte", "belo_horizonte_mg", 3, BigDecimal(421.50)),
            PacoteModel("Recife", "recife_pe", 4, BigDecimal(754.20)),
            PacoteModel("Rio de Janeiro", "rio_de_janeiro_rj", 6, BigDecimal(532.55)),
            PacoteModel("Salvador", "salvador_ba", 5, BigDecimal(899.99)),
            PacoteModel("Foz do Iguaçu", "foz_do_iguacu_pr", 1, BigDecimal(289.90))
        )
    }
}