package ni.edu.uca.systemfits.ui.dialogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ni.edu.uca.systemfits.databinding.FragmentDialogInputAgregarGrupoMuscularBinding

class dialog_input_agregar_grupo_muscular : Fragment() {

    private lateinit var binding: FragmentDialogInputAgregarGrupoMuscularBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogInputAgregarGrupoMuscularBinding.inflate(inflater, container, false)
        return binding.root
    }
}