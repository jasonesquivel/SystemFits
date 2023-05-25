package ni.edu.uca.systemfits.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.systemfits.data.database.entities.Comidas
import ni.edu.uca.systemfits.data.database.entities.Ejercicios
import ni.edu.uca.systemfits.data.database.entities.Medidas
import ni.edu.uca.systemfits.databinding.ItemComidasBinding
import ni.edu.uca.systemfits.databinding.ItemEjerciciosBinding
import ni.edu.uca.systemfits.databinding.ItemMedidasBinding

class MenuPrincipalAdapter(
    private var comidas: List<Comidas>,
    private var medidas: List<Medidas>,
    private var ejercicios: List<Ejercicios>,
    private var onItemClick: (Any) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateData(
        newComidas: List<Comidas>,
        newMedidas: List<Medidas>,
        newEjercicios: List<Ejercicios>
    ) {
        comidas = newComidas
        medidas = newMedidas
        ejercicios = newEjercicios
        notifyDataSetChanged()
    }

    companion object {
        private const val ITEM_TYPE_COMIDA = 0
        private const val ITEM_TYPE_MEDIDA = 1
        private const val ITEM_TYPE_EJERCICIO = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ITEM_TYPE_COMIDA -> {
                val binding = ItemComidasBinding.inflate(inflater, parent, false)
                ComidaViewHolder(binding)
            }
            ITEM_TYPE_MEDIDA -> {
                val binding = ItemMedidasBinding.inflate(inflater, parent, false)
                MedidaViewHolder(binding)
            }
            ITEM_TYPE_EJERCICIO -> {
                val binding = ItemEjerciciosBinding.inflate(inflater, parent, false)
                EjercicioViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is ComidaViewHolder -> {
                val comida = item as Comidas
                holder.bind(comida)
            }
            is MedidaViewHolder -> {
                val medida = item as Medidas
                holder.bind(medida)
            }
            is EjercicioViewHolder -> {
                val ejercicio = item as Ejercicios
                holder.bind(ejercicio)
            }
        }
    }

    override fun getItemCount(): Int {
        return comidas.size + medidas.size + ejercicios.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when (item) {
            is Comidas -> ITEM_TYPE_COMIDA
            is Medidas -> ITEM_TYPE_MEDIDA
            is Ejercicios -> ITEM_TYPE_EJERCICIO
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

    private fun getItem(position: Int): Any {
        val comidaSize = comidas.size
        val medidaSize = medidas.size

        return when {
            position < comidaSize -> comidas[position]
            position < comidaSize + medidaSize -> medidas[position - comidaSize]
            else -> ejercicios[position - comidaSize - medidaSize]
        }
    }

    inner class ComidaViewHolder(private val binding: ItemComidasBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val comida = getItem(position) as Comidas
                    onItemClick(comida)
                }
            }
        }

        fun bind(comida: Comidas) {
            binding.tvcomida.text = comida.comida
            binding.tvcalorias.text = "${comida.calorias.toString()} calorias"
        }
    }

    inner class MedidaViewHolder(private val binding: ItemMedidasBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val medida = getItem(position) as Medidas
                    onItemClick(medida)
                }
            }
        }

        fun bind(medida: Medidas) {
            binding.tvmusculo.text = medida.musculo
            binding.tvmedida.text = "${medida.tamaño.toString()} cm"
        }
    }

    inner class EjercicioViewHolder(private val binding: ItemEjerciciosBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val ejercicio = getItem(position) as Ejercicios
                    onItemClick(ejercicio)
                }
            }
        }

        fun bind(ejercicio: Ejercicios) {
            binding.tvNombreEjercicio.text = ejercicio.nombre
            binding.tvCantidadRepeticiones.text = "Repeticiones: ${ejercicio.repeticiones}"
            binding.tvNumeroSeries.text = "Series: ${ejercicio.series}"
        }
    }
}
