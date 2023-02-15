package com.example.orgs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.orgs.database.converter.Converters
import com.example.orgs.database.dao.ProdutoDao
import com.example.orgs.database.dao.UsuarioDao
import com.example.orgs.entities.Produto
import com.example.orgs.entities.Usuario

@Database(
    entities = [
        Produto::class,
        Usuario::class,
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao

    abstract fun usuarioDao(): UsuarioDao

    companion object {
        @Volatile
        private var db: AppDataBase? = null

        fun instancia(context: Context): AppDataBase {
            return db ?: Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                "orgs.db"
            ).fallbackToDestructiveMigration()
                .build().also {
                    db = it
                }
        }
    }
}