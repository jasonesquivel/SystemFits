package ni.edu.uca.systemfits.vistas

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
import androidx.navigation.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ni.edu.uca.systemfits.MainActivity
import ni.edu.uca.systemfits.R
import ni.edu.uca.systemfits.databinding.FragmentRegistroBinding
import ni.edu.uca.systemfits.modelo.Registros
import ni.edu.uca.systemfits.modelo.RegistrosViewModel
import java.util.*

class Registro : Fragment() {

    private lateinit var binding: FragmentRegistroBinding
    private val viewModel: RegistrosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistroBinding.inflate(inflater, container, false)
        return binding.root
    }

    //FUNCIÓN PARA VALIDAR CAMPOS DE TEXTO DEL REGISTRO//
    private fun validarCamposLogin(): Boolean {
        var valido = true

        val nombre = binding.etNombre.text.toString()
        val apellido = binding.etApellido.text.toString()
        val fechaNac = binding.tvFechaNac.text.toString()
        val genero = binding.spGenero.selectedItem.toString()
        val peso = binding.etPeso.text.toString()
        val altura = binding.etAltura.text.toString()
        val usuario = binding.etUsuario.text.toString()
        val contraseña = binding.etContraseA.text.toString()

        if (nombre.isBlank()) {
            binding.etNombre.setError("Este campo no puede estar vacío")
            valido = false
        }

        if (apellido.isBlank()) {
            binding.etApellido.setError("Este campo no puede estar vacío")
            valido = false
        }

        if (fechaNac.isBlank()) {
            Toast.makeText(
                requireContext(),
                "Debe seleccionar una fecha de nacimiento",
                Toast.LENGTH_SHORT
            ).show()
            valido = false
        }

        if (genero.isBlank() || genero == "Seleccione un género") {
            Toast.makeText(requireContext(), "Debe seleccionar un género", Toast.LENGTH_SHORT)
                .show()
            valido = false
        }

        if (peso.isBlank()) {
            binding.etPeso.setError("Este campo no puede estar vacío")
            valido = false
        }

        if (altura.isBlank()) {
            binding.etAltura.setError("Este campo no puede estar vacío")
            valido = false
        }

        if (usuario.isBlank()) {
            binding.etUsuario.setError("Este campo no puede estar vacío")
            valido = false
        }

        if (contraseña.isBlank()) {
            binding.etContraseA.setError("Este campo no puede estar vacío")
            valido = false
        }
        return valido
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etAltura.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                val altura: String = binding.etAltura.text.toString()
                if (altura.length == 3 && !altura.contains(".")) {
                    binding.etAltura.setText("${altura[0]}.${altura.substring(1)}")
                    binding.etAltura.setSelection(binding.etAltura.text.length)
                }
            }
        })
        binding.etPeso.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val peso: String = binding.etPeso.text.toString()
                if (peso.length == 3 && !peso.contains(".")) {
                    binding.etPeso.setText("${peso.substring(0, 2)}.${peso.substring(2)}")
                    binding.etPeso.setSelection(binding.etPeso.text.length) // mover el cursor al final
                }
            }
        })

        val fechaNac: TextView = binding.tvFechaNac
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

        val spinner: Spinner = binding.spGenero
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.genero_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        binding.tvInicioSesion.setOnClickListener {
            it.findNavController().navigate(R.id.login)
        }

        binding.btnRegistro.setOnClickListener {
            try {
                if (validarCamposLogin()) {
                    val nombre = binding.etNombre.text.toString()
                    val apellido = binding.etApellido.text.toString()
                    val fechaNac = binding.tvFechaNac.text.toString()
                    val genero = binding.spGenero.selectedItem.toString()
                    val peso = binding.etPeso.text.toString().toDouble()
                    val altura = binding.etAltura.text.toString().toDouble()
                    val usuario = binding.etUsuario.text.toString()
                    val contraseña = binding.etContraseA.text.toString()

                    val registros = Registros(
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
                        try {
                            viewModel.insertar(registros)
                            withContext(Dispatchers.Main) {
                                it.findNavController().navigate(R.id.login)
                            }
                        } catch (ex: Exception) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    requireContext(), "Error : ${ex.toString()}", Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                }
            } catch (ex: Exception) {
                Toast.makeText(
                    requireContext(), "Error : ${ex.toString()}", Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}