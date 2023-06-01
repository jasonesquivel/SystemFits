package ni.edu.uca.systemfits.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ni.edu.uca.systemfits.databinding.FragmentInputDialogEditarSemanaEjerciciosBinding

class input_dialog_editar_semana_ejercicios : Fragment() {
    private lateinit var binding: FragmentInputDialogEditarSemanaEjerciciosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentInputDialogEditarSemanaEjerciciosBinding.inflate(inflater, container, false)
        return binding.root
    }
}