package ni.edu.uca.systemfits.ui.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ni.edu.uca.systemfits.R
import ni.edu.uca.systemfits.data.sharedPrefernces.SharedPrefManager
import ni.edu.uca.systemfits.databinding.FragmentConfiguracionBinding
import ni.edu.uca.systemfits.data.database.entities.Registros
import ni.edu.uca.systemfits.ui.viewmodel.RegistrosViewModel
import java.util.*

class configuracion : Fragment() {
    private lateinit var binding: FragmentConfiguracionBinding
    private val viewModel: RegistrosViewModel by viewModels()
    private lateinit var sharedPrefManager: SharedPrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfiguracionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etAlturaEditable.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                val altura: String = binding.etAlturaEditable.text.toString()
                if (altura.length == 3 && !altura.contains(".")) {
                    binding.etAlturaEditable.setText("${altura[0]}.${altura.substring(1)}")
                    binding.etAlturaEditable.setSelection(binding.etAlturaEditable.text.length)
                }
            }
        })
        binding.etPesoEditable.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val peso: String = binding.etPesoEditable.text.toString()
                if (peso.length == 3 && !peso.contains(".")) {
                    binding.etPesoEditable.setText("${peso.substring(0, 2)}.${peso.substring(2)}")
                    binding.etPesoEditable.setSelection(binding.etPesoEditable.text.length) // mover el cursor al final
                }
            }
        })

        val fechaNac: TextView = binding.tvFechaNacEditable
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        fechaNac.setOnClickListener {
            val dpd = DatePickerDialog(requireContext(), { _, year, monthOfYear, dayOfMonth ->
                fechaNac.text = "$dayOfMonth/${monthOfYear + 1}/$year"
            }, year, month, day)
            dpd.show()
        }

        sharedPrefManager = SharedPrefManager(requireContext())

        val spinner: Spinner = binding.spGenero
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.genero_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        val nombreUser = sharedPrefManager.username
        viewModel.obtenerUsuario(nombreUser!!).observe(viewLifecycleOwner, Observer { usuario ->
            if (usuario != null) {
                binding.etId.setText(usuario.id.toString())
                binding.etNombreEditable.setText(usuario.nombre)
                binding.etApellidoEditable.setText(usuario.apellido)
                binding.tvFechaNacEditable.text = usuario.fechaNac
                val generoArray = resources.getStringArray(R.array.genero_array)
                val generoIndex = generoArray.indexOf(usuario.genéro)
                if (generoIndex >= 0) {
                    binding.spGenero.setSelection(generoIndex)
                }
                binding.etPesoEditable.setText(usuario.peso.toString())
                binding.etAlturaEditable.setText(usuario.altura.toString())
                binding.etUsuarioEditable.setText(usuario.usuario)
                binding.etContraseAEditable.setText(usuario.contraseña)
            } else {
                Toast.makeText(requireContext(), "Usuario null", Toast.LENGTH_SHORT).show()
            }
        })

        binding.btnEditarPerfil.setOnClickListener {
            try {
                val id = binding.etId.text.toString().toInt()
                val usuario = binding.etUsuarioEditable.text.toString()
                val nombre = binding.etNombreEditable.text.toString()
                val apellido = binding.etApellidoEditable.text.toString()
                val contraseña = binding.etContraseAEditable.text.toString()
                val fechaNac = binding.tvFechaNacEditable.text.toString()
                val genero = binding.spGenero.selectedItem.toString()
                val peso = binding.etPesoEditable.text.toString().toDoubleOrNull() ?: 0.0
                val altura = binding.etAlturaEditable.text.toString().toDoubleOrNull() ?: 0.0

                val usuarioActualizado = Registros(
                    id = id,
                    nombre = nombre,
                    apellido = apellido,
                    fechaNac = fechaNac,
                    genéro = genero,
                    peso = peso,
                    altura = altura,
                    usuario = usuario,
                    contraseña = contraseña
                )

                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.actualizar(usuarioActualizado)
                }
            } catch (ex: Exception) {
                Toast.makeText(
                    requireContext(), "Error : ${ex.toString()}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}