package com.example.orgs.database.dao

import androidx.room.*
import com.example.orgs.entities.Produto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProdutoDao {
    @Query("SELECT * FROM PRODUTO")
    fun buscaTodos() : Flow<List<Produto>>

    @Query("SELECT * FROM Produto WHERE usuarioId=:id")
    fun buscaTodosDoUsuario(id: String) : Flow<List<Produto>>

    @Query("SELECT * FROM PRODUTO WHERE ID =:id")
    fun buscaPeloId(id: Long) : Flow<Produto?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salva(vararg produto: Produto)

    @Delete()
    suspend fun delete(vararg produto: Produto)
}