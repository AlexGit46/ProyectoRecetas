package net.azarquiel.recetasclase.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Room
import android.content.Context

@Database(entities = [Categoria::class,Receta::class], version = 1)
abstract class RecetasDB: RoomDatabase() {
    abstract fun categoriaDao(): CategoriaDao
    abstract fun recetaDao(): RecetaDao
    companion object {
        @Volatile
        private var INSTANCE: RecetasDB? = null

        fun getDatabase(context: Context): RecetasDB? {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        RecetasDB::class.java, "recetas.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}
