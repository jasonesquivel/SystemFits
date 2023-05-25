package ni.edu.uca.systemfits.ui.view.dialogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ni.edu.uca.systemfits.R
import ni.edu.uca.systemfits.databinding.FragmentDialogInputEditarComidaBinding

class dialog_input_editar_comida : Fragment() {

    private lateinit var binding: FragmentDialogInputEditarComidaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog_input_editar_comida, container, false)
    }
}