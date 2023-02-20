package com.example.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.orgs.entities.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {
    @Insert
    suspend fun salva(usuario: Usuario)

    @Query("SELECT * FROM USUARIO WHERE id=:usuarioId AND senha=:senha")
    suspend fun autentica(usuarioId: String, senha: String) : Usuario?

    @Query("SELECT * FROM USUARIO WHERE id=:usuarioId")
     fun buscaPorId(usuarioId:String) : Flow<Usuario>
}