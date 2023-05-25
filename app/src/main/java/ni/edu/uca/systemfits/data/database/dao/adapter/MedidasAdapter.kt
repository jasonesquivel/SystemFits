package ni.edu.uca.systemfits.data.database.dao.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.systemfits.databinding.ItemMedidasBinding
import ni.edu.uca.systemfits.data.database.entities.Medidas

class MedidasAdapter(private val onItemClick: (Medidas) -> Unit) :
    RecyclerView.Adapter<MedidasAdapter.MedidasViewHolder>() {

    private var Medidas = listOf<Medidas>()

    fun setMedidas(Medidas: List<Medidas>) {
        this.Medidas = Medidas
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedidasViewHolder {
        val binding =
            ItemMedidasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MedidasViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: MedidasViewHolder, position: Int) {
        holder.bind(Medidas[position])
    }

    override fun getItemCount(): Int = Medidas.size

    inner class MedidasViewHolder(
        private val binding: ItemMedidasBinding,
        private val onItemClick: (Medidas) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(Medidas: Medidas) {
            binding.tvmusculo.text = Medidas.musculo
            binding.tvmedida.text = "${Medidas.tamaño.toString()} cm"

            binding.root.setOnClickListener { onItemClick(Medidas) }
        }
    }
}