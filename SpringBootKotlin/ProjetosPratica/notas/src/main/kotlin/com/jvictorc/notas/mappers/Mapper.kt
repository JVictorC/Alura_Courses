package com.jvictorc.notas.mappers

interface Mapper<FormaInicial,FormaFinal> {
    fun map(formaInicial: FormaInicial) : FormaFinal
}