package ni.edu.uca.systemfits.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.systemfits.data.database.entities.Ejercicios
import ni.edu.uca.systemfits.databinding.ItemEjerciciosBinding

class EjerciciosAdapter(private val onItemClick: (Ejercicios) -> Unit) :
    RecyclerView.Adapter<EjerciciosAdapter.EjerciciosViewHolder>() {

    private var Ejercicios = listOf<Ejercicios>()

    fun setEjercicios(Ejercicios: List<Ejercicios>) {
        this.Ejercicios = Ejercicios
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EjerciciosViewHolder {
        val binding =
            ItemEjerciciosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EjerciciosViewHolder(binding, onItemClick)
    }


    override fun onBindViewHolder(holder: EjerciciosViewHolder, position: Int) {
        holder.bind(Ejercicios[position])
    }

    override fun getItemCount(): Int = Ejercicios.size

    inner class EjerciciosViewHolder(
        private val binding: ItemEjerciciosBinding,
        private val onItemClick: (Ejercicios) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(Ejercicios: Ejercicios) {
            binding.tvNombreEjercicio.text = Ejercicios.nombre
            binding.tvCantidadRepeticiones.text = "Repeticiones: ${Ejercicios.repeticiones}"
            binding.tvNumeroSeries.text = "Series: ${Ejercicios.series}"

            binding.root.setOnClickListener { onItemClick(Ejercicios) }
        }
    }

}