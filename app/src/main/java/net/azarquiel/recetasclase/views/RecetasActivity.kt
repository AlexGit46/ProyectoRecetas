package net.azarquiel.recetasclase.views

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.View
import android.widget.SearchView
import kotlinx.android.synthetic.main.activity_recetas.*
import kotlinx.android.synthetic.main.content_recetas.*
import net.azarquiel.recetasclase.R
import net.azarquiel.recetasclase.adapter.CustomAdapterRecetas
import net.azarquiel.recetasclase.model.Categoria
import net.azarquiel.recetasclase.model.Receta
import net.azarquiel.recetasclase.viewmodel.RecetaViewModel
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class RecetasActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var adapter: CustomAdapterRecetas

    private lateinit var categoria: Categoria
    private lateinit var recetas: List<Receta>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recetas)
        setSupportActionBar(toolbar)

        categoria = intent.getSerializableExtra("categoria") as Categoria

        val recetaViewModel = ViewModelProviders.of(this).get(RecetaViewModel::class.java)
        showData()
        doAsync {
            recetas = recetaViewModel.getRecetas(categoria.id)
            uiThread {
                adapter.setRecetas(recetas)
            }
        }
    }

    private fun showData() {
        adapter = CustomAdapterRecetas(this, R.layout.rowreceta)
        rvRecetas.layoutManager = LinearLayoutManager(this)
        rvRecetas.adapter = adapter
    }

    fun onClickReceta(view: View){
        val receta = view.tag as Receta
        val intent = Intent(this, RecetaActivity::class.java)
        intent.putExtra("receta", receta)
        startActivity(intent)
    }

    private lateinit var searchView: SearchView

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        // ************* <Filtro> ************
        val searchItem = menu.findItem(R.id.search)
        searchView = searchItem.actionView as SearchView
        searchView.setQueryHint("Search...")
        searchView.setOnQueryTextListener(this)
        // ************* </Filtro> ************

        return true
    }

    // ************* <Filtro> ************
    override fun onQueryTextChange(query: String): Boolean {
        adapter.setRecetas(recetas.filter { receta -> receta.titulo.toLowerCase().contains(query.toLowerCase()) })
        return false
    }

    override fun onQueryTextSubmit(text: String): Boolean {
        return false
    }
    // ************* </Filtro> ************

}
