package br.com.alura.ceep.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import br.com.alura.ceep.model.Nota
import kotlinx.coroutines.flow.Flow

@Dao
interface NotaDao {

    @Insert(onConflict = REPLACE)
    suspend fun salva(note: Nota)

    @Query("SELECT * FROM Nota WHERE desativada=0")
    fun buscaTodasNotasAtivas(): Flow<List<Nota>>

    @Query("SELECT * FROM Nota")
    fun buscaTodas(): Flow<List<Nota>>

    @Query("SELECT * FROM Nota WHERE id = :id  AND  desativada=0")
    fun buscaPorId(id: String): Flow<Nota>

    @Query("UPDATE Nota SET desativada=1, sincronizada=0 WHERE id = :id")
    suspend fun desativaNota(id: String)

    @Insert(onConflict = REPLACE)
    suspend fun salvaTodas(notas: List<Nota>)

    @Query("DELETE FROM Nota WHERE id = :id")
    suspend fun removeNota(id: String)
}