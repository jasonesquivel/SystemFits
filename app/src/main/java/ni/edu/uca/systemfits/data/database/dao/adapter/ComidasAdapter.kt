package ni.edu.uca.systemfits.data.database.dao.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.systemfits.databinding.ItemComidasBinding
import ni.edu.uca.systemfits.data.database.entities.Comidas

class ComidasAdapter(private val onItemClick: (Comidas) -> Unit) :
    RecyclerView.Adapter<ComidasAdapter.ComidasViewHolder>() {

    private var Comidas = listOf<Comidas>()

    fun setComidas(Comidas: List<Comidas>) {
        this.Comidas = Comidas
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComidasViewHolder {
        val binding = ItemComidasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComidasViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ComidasViewHolder, position: Int) {
        holder.bind(Comidas[position])
    }

    override fun getItemCount(): Int = Comidas.size

    inner class ComidasViewHolder(
        private val binding: ItemComidasBinding,
        private val onItemClick: (Comidas) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(Comidas: Comidas) {
            binding.tvcomida.text = Comidas.comida
            binding.tvcalorias.text = "${Comidas.calorias.toString()} calorias"

            binding.root.setOnClickListener { onItemClick(Comidas) }
        }
    }
}