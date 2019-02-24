package net.azarquiel.recetasclase.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import net.azarquiel.recetasclase.model.Categoria
import net.azarquiel.recetasclase.model.CategoriaRepository
import net.azarquiel.recetasclase.model.Receta
import net.azarquiel.recetasclase.model.RecetaRepository

class CategoriaViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: CategoriaRepository =
        CategoriaRepository(application)

    fun getCategorias():List<Categoria>{
        return repository.getCategorias()
    }
}

class RecetaViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: RecetaRepository =
        RecetaRepository(application)

    fun getRecetas(categoria:Int):List<Receta>{
        return repository.getRecetas(categoria)
    }
}
