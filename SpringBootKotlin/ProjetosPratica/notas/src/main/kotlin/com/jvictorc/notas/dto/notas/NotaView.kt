package com.jvictorc.notas.dto.notas

import com.jvictorc.notas.model.Nota

data class NotaView(
    val data: List<Nota>,
    val quantidade: Int

)