package ni.edu.uca.systemfits

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ni.edu.uca.systemfits.databinding.FragmentDialogInputEditarMedidaBinding

class dialog_input_editar_medida : Fragment() {
    private lateinit var binding: FragmentDialogInputEditarMedidaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog_input_editar_medida, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etMedidaEdit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val peso: String = binding.etMedidaEdit.text.toString()
                if (peso.length == 3 && !peso.contains(".")) {
                    binding.etMedidaEdit.setText("${peso.substring(0, 2)}.${peso.substring(2)}")
                    binding.etMedidaEdit.setSelection(binding.etMedidaEdit.text.length) // mover el cursor al final
                }
            }
        })

    }
}