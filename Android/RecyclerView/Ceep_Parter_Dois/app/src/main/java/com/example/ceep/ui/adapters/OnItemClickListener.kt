package com.example.ceep.ui.adapters

import com.example.ceep.model.Nota

fun interface OnItemClickListener {
    fun call(nota:  Nota?, index: Int?)
}