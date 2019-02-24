package net.azarquiel.recetasclase.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.rowreceta.view.*
import net.azarquiel.recetasclase.model.Receta


class CustomAdapterRecetas(val context: Context, val layout: Int) : RecyclerView.Adapter<CustomAdapterRecetas.ViewHolder>() {

    private var dataList: List<Receta> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    internal fun setRecetas(recetas: List<Receta>) {
        this.dataList = recetas
        notifyDataSetChanged()
    }

    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Receta){
            itemView.tvReceta.text = dataItem.titulo
            itemView.tag = dataItem
        }

    }
}
