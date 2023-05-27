package ni.edu.uca.systemfits.ui.dialogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ni.edu.uca.systemfits.R
import ni.edu.uca.systemfits.databinding.FragmentDialogInputAgregarEjercicioBinding

class dialog_input_agregar_Ejercicio : Fragment(){

    private lateinit var binding: FragmentDialogInputAgregarEjercicioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_dialog_input_agregar_comida, container, false)
    }
}