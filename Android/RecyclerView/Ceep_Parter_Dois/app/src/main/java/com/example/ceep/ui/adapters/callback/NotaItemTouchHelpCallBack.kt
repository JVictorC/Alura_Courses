package com.example.ceep.ui.adapters.callback

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.ceep.dao.NotaDAO
import com.example.ceep.ui.adapters.RecyclerNotasAdapter

class NotaItemTouchHelpCallBack(private val adapter: RecyclerNotasAdapter) : ItemTouchHelper.Callback() {
    private val dao = NotaDAO()

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val marcacoesDeDeslize = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        val marcacoesDeArrasatar = ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        return makeMovementFlags(marcacoesDeArrasatar, marcacoesDeDeslize)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val positionInitial = viewHolder.adapterPosition
        val positionFinal = target.adapterPosition
       dao.troca(positionInitial, positionFinal)


        adapter.troca(positionInitial, positionFinal)

        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        dao.remove(position)

        adapter.remove(dao.todos(), position)
    }

}
