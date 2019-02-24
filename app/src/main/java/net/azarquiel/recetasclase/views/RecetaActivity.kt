package net.azarquiel.recetasclase.views

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_receta.*
import kotlinx.android.synthetic.main.dialogimage.view.*
import net.azarquiel.recetasclase.R
import net.azarquiel.recetasclase.databinding.ActivityRecetaBinding
import net.azarquiel.recetasclase.model.Receta


class RecetaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        var binding:ActivityRecetaBinding = DataBindingUtil.setContentView(this, R.layout.activity_receta)
        val receta = intent.getSerializableExtra("receta") as Receta

        binding.receta = receta
        fab.setOnClickListener {
            showImage(receta)
        }
    }

    private fun showImage(receta: Receta) {

        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Image")
        val inflater = layoutInflater
        val layout = inflater.inflate(R.layout.dialogimage, null)
        if (receta.foto!=""){
            val url = "http://www.mor.ninja/mobil/app_resim/yemek/yemek_es/b/${receta.foto}"
            Picasso.with(this)
                .load(url)
                .resize(890, 500)
                .into(layout.ivDialog)}
        else {
            layout.ivDialog.setImageResource(R.drawable.noimage)
        }
        builder.setView(layout)
        builder.setPositiveButton("Aceptar"
        ) { _, _ ->
        }
        builder.show()
    }

}
