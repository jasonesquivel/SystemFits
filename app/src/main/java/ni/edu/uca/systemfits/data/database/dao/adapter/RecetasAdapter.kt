package ni.edu.uca.systemfits.data.database.dao.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.systemfits.R
import ni.edu.uca.systemfits.data.model.Recetas

class RecetasAdapter(private var recetas: List<Recetas>) :
    RecyclerView.Adapter<RecetasAdapter.ViewHolder>() {

    fun setData(recetas: List<Recetas>) {
        this.recetas = recetas
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.tvRecetaNombre)
        private val descripcionTextView: TextView = itemView.findViewById(R.id.tvRecetaDescripcion)
        private val caloriasTextView: TextView = itemView.findViewById(R.id.tvRecetaCalorias)

        fun bind(receta: Recetas) {
            nombreTextView.text = receta.nombre
            descripcionTextView.text = receta.descripcion
            caloriasTextView.text = receta.calorias
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recetas, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val receta = recetas[position]
        holder.bind(receta)
    }

    override fun getItemCount(): Int {
        return recetas.size
    }
}