package ni.edu.uca.systemfits.ui.dialogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ni.edu.uca.systemfits.databinding.FragmentDialogInputEditarEjercicioBinding

class dialog_input_editar_ejercicio : Fragment() {
    private lateinit var binding: FragmentDialogInputEditarEjercicioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogInputEditarEjercicioBinding.inflate(inflater, container, false)
        return binding.root
    }
}
