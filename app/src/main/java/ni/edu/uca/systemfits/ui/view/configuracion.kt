package ni.edu.uca.systemfits.ui.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ni.edu.uca.systemfits.R
import ni.edu.uca.systemfits.data.database.entities.Registros
import ni.edu.uca.systemfits.databinding.FragmentConfiguracionBinding
import ni.edu.uca.systemfits.ui.sharedpreferences.SharedPrefUtil
import ni.edu.uca.systemfits.ui.viewmodel.RegistrosViewModel
import java.util.*

class configuracion : Fragment() {
    private lateinit var binding: FragmentConfiguracionBinding
    private val viewModel: RegistrosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfiguracionBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun actualizarDatosUsuario() {
        val nombreUser = SharedPrefUtil.obtenerUsuario(requireContext())
        viewModel.obtenerUsuario(nombreUser!!.toString())
            .observe(viewLifecycleOwner, Observer { usuario ->
                if (usuario != null) {
                    binding.etNombre.setText(usuario.nombre)
                    binding.etApellido.setText(usuario.apellido)
                    binding.tvFechaNac.text = usuario.fechaNac
                    val generoArray = resources.getStringArray(R.array.genero_array)
                    val generoIndex = generoArray.indexOf(usuario.genéro)
                    if (generoIndex >= 0) {
                        binding.spGenero.setSelection(generoIndex)
                    }
                    binding.etPeso.setText(usuario.peso.toString())
                    binding.etAltura.setText(usuario.altura.toString())
                    binding.etUsuario.setText(usuario.usuario)
                    binding.etContraseA.setText(usuario.contraseña)
                } else {
                    Toast.makeText(requireContext(), "Usuario null", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun validarCamposPerfil(): Boolean {
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

        binding.btnMostrarContraseA.setOnClickListener {
            val isPasswordVisible = binding.etContraseA.transformationMethod == HideReturnsTransformationMethod.getInstance()
            if (isPasswordVisible) {
                binding.etContraseA.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.btnMostrarContraseA.text = "Mostrar"
            } else {
                binding.etContraseA.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.btnMostrarContraseA.text = "Ocultar"
            }
        }

        binding.etAltura.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val altura: String = binding.etAltura.text.toString()
                if (altura.length == 3 && !altura.contains(".")) {
                    binding.etAltura.setText("${altura[0]}.${altura.substring(1)}")
                    binding.etAltura.setSelection(binding.etAltura.text.length)
                }
            }
        })

        binding.etPeso.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val peso: String = binding.etPeso.text.toString()
                if (peso.length == 3 && !peso.contains(".")) {
                    binding.etPeso.setText("${peso.substring(0, 2)}.${peso.substring(2)}")
                    binding.etPeso.setSelection(binding.etPeso.text.length)
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

        actualizarDatosUsuario()

        binding.btnEditarPerfil.setOnClickListener {
            try {
                if (validarCamposPerfil()) {
                    val usuarioE = binding.etUsuario.text.toString()
                    val nombre = binding.etNombre.text.toString()
                    val apellido = binding.etApellido.text.toString()
                    val contraseña = binding.etContraseA.text.toString()
                    val fechaNac = binding.tvFechaNac.text.toString()
                    val genero = binding.spGenero.selectedItem.toString()
                    val peso = binding.etPeso.text.toString().toDoubleOrNull() ?: 0.0
                    val altura = binding.etAltura.text.toString().toDoubleOrNull() ?: 0.0

                    val usuarioActualizado = Registros(
                        usuario = usuarioE,
                        nombre = nombre,
                        apellido = apellido,
                        fechaNac = fechaNac,
                        genéro = genero,
                        peso = peso,
                        altura = altura,
                        contraseña = contraseña
                    )

                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.actualizarRegistro(usuarioActualizado)
                    }
                }
            } catch (ex: Exception) {
                Toast.makeText(
                    requireContext(), "Error: ${ex.toString()}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }
}