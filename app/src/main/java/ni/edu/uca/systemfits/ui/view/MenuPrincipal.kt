package ni.edu.uca.systemfits.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_comida.*
import kotlinx.android.synthetic.main.fragment_menu_principal.*
import ni.edu.uca.systemfits.ui.adapter.MenuPrincipalAdapter
import ni.edu.uca.systemfits.data.database.entities.Comidas
import ni.edu.uca.systemfits.data.database.entities.Medidas
import ni.edu.uca.systemfits.data.database.entities.Ejercicios
import ni.edu.uca.systemfits.databinding.FragmentMenuPrincipalBinding
import ni.edu.uca.systemfits.ui.viewmodel.ComidasViewModel
import ni.edu.uca.systemfits.ui.viewmodel.EjerciciosViewModel
import ni.edu.uca.systemfits.ui.viewmodel.MedidasViewModel

class MenuPrincipal : Fragment() {

    private lateinit var binding: FragmentMenuPrincipalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuPrincipalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelComidas: ComidasViewModel by viewModels()
        val viewModelMedidas: MedidasViewModel by viewModels()
        val viewModelEjercicios: EjerciciosViewModel by viewModels()


        val menuPrincipalAdapter =
            MenuPrincipalAdapter(emptyList(), emptyList(), emptyList()) { item ->
                when (item) {
                    is Comidas -> {
                        // Lógica para manejar el clic en un item de Comidas
                    }
                    is Medidas -> {
                        // Lógica para manejar el clic en un item de Medidas
                    }
                    is Ejercicios -> {
                        // Lógica para manejar el clic en un item de Ejercicios
                    }
                    else -> {
                        // Lógica para manejar otros tipos de items (si corresponde)
                    }
                }
            }
        binding.rcvMenu.adapter = menuPrincipalAdapter
        binding.rcvMenu.layoutManager = LinearLayoutManager(requireContext())

        viewModelComidas.todos.observe(viewLifecycleOwner) { comidas ->
            val medidas: List<Medidas> = viewModelMedidas.todos.value ?: emptyList()
            val ejercicios: List<Ejercicios> = viewModelEjercicios.todos.value ?: emptyList()
            menuPrincipalAdapter.updateData(comidas, medidas, ejercicios)
        }

        viewModelMedidas.todos.observe(viewLifecycleOwner) { medidas ->
            val comidas: List<Comidas> = viewModelComidas.todos.value ?: emptyList()
            val ejercicios: List<Ejercicios> = viewModelEjercicios.todos.value ?: emptyList()
            menuPrincipalAdapter.updateData(comidas, medidas, ejercicios)
        }

        viewModelEjercicios.todos.observe(viewLifecycleOwner) { ejercicios ->
            val comidas: List<Comidas> = viewModelComidas.todos.value ?: emptyList()
            val medidas: List<Medidas> = viewModelMedidas.todos.value ?: emptyList()
            menuPrincipalAdapter.updateData(comidas, medidas, ejercicios)
        }

        viewModelComidas.getTotalCaloriasConsumidas().observe(viewLifecycleOwner) { totalCalorias ->
            tvCalorias.text = totalCalorias.toString()
        }
    }
}
