package com.example.orgs.database.dao

import androidx.room.*
import com.example.orgs.entities.Produto

@Dao
interface ProdutoDao {
    @Query("SELECT * FROM PRODUTO")
    fun buscaTodos() : List<Produto>

    @Query("SELECT * FROM PRODUTO WHERE ID =:id")
    fun buscaPeloId(id: Long) : Produto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salva(vararg produto: Produto)

    @Delete()
    fun delete(vararg produto: Produto)
}