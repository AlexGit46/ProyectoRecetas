package net.azarquiel.recetasclase.model

import android.app.Application

class CategoriaRepository(application: Application) {

    val categoriaDao = RecetasDB.getDatabase(application)!!.categoriaDao()
    fun getCategorias():List<Categoria> {
        return categoriaDao.getCategorias()
    }
}

class RecetaRepository(application: Application)  {

    val recetaDao = RecetasDB.getDatabase(application)!!.recetaDao()
    fun getRecetas(categoria:Int): List<Receta>{
        return recetaDao.getRecetas(categoria)
    }

}