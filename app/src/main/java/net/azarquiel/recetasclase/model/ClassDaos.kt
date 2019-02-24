package net.azarquiel.recetasclase.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query

@Dao
interface CategoriaDao {

    @Query("SELECT * from categoria ORDER BY nombre ASC")
    fun getCategorias(): List<Categoria>
}

@Dao
interface RecetaDao {

    @Query("SELECT * from receta where categoria = :categoria ORDER BY id ASC")
    fun getRecetas(categoria: Int): List<Receta>
}