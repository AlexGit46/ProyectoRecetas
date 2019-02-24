package net.azarquiel.recetasclase.views

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import net.azarquiel.recetasclase.R
import net.azarquiel.recetasclase.adapter.CustomAdapterCategorias
import net.azarquiel.recetasclase.model.Categoria
import net.azarquiel.recetasclase.util.Util
import net.azarquiel.recetasclase.viewmodel.CategoriaViewModel
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: CustomAdapterCategorias

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Util.inyecta(this)

        val categoriaViewModel = ViewModelProviders.of(this).get(CategoriaViewModel::class.java)
        showData()
        doAsync {
            val categorias = categoriaViewModel.getCategorias()
            uiThread {
                adapter.setCategorias(categorias)
            }
        }
    }

    private fun showData() {
        adapter = CustomAdapterCategorias(this, R.layout.rowcategoria)
        rvCategorias.layoutManager = LinearLayoutManager(this)
        rvCategorias.adapter = adapter
    }

    fun onClickCategoria(view:View){
        val categoria = view.tag as Categoria
        val intent = Intent(this, RecetasActivity::class.java)
        intent.putExtra("categoria", categoria)
        startActivity(intent)
    }
}
