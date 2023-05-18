package ni.edu.uca.systemfits.vistas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val fechaNac = binding.etFechaNac.text.toString()
        val genero = binding.etGenero.text.toString()
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
            binding.etFechaNac.setError("Este campo no puede estar vacío")
            valido = false
        }

        if (genero.isBlank()) {
            binding.etGenero.setError("Este campo no puede estar vacío")
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

        binding.tvInicioSesion.setOnClickListener {
            it.findNavController().navigate(R.id.login)
        }

        binding.btnRegistro.setOnClickListener {
            try {
                if (validarCamposLogin()) {
                    val nombre = binding.etNombre.text.toString()
                    val apellido = binding.etApellido.text.toString()
                    val fechaNac = binding.etFechaNac.text.toString()
                    val genero = binding.etGenero.text.toString()
                    val peso = binding.etPeso.text.toString().toInt()
                    val altura = binding.etAltura.text.toString().toInt()
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
                                (activity as MainActivity).showBottomNavigationView()
                                it.findNavController().navigate(R.id.registro_menuPrincipal)
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