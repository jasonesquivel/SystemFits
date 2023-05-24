package ni.edu.uca.systemfits.vistas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import ni.edu.uca.systemfits.MainActivity
import ni.edu.uca.systemfits.R
import ni.edu.uca.systemfits.SharedPrefManager
import ni.edu.uca.systemfits.databinding.FragmentLoginBinding
import ni.edu.uca.systemfits.modelo.RegistrosViewModel

class Login : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: RegistrosViewModel by viewModels()
    private lateinit var sharedPrefManager: SharedPrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefManager = SharedPrefManager(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    //FUNCIÓN PARA VALIDAR CAMPOS DE TEXTO DEL LOGIN//
    private fun validarCamposLogin(): Boolean {
        var valido = true

        val usuario = binding.etUsername.text.toString()
        val contraseña = binding.etPassword.text.toString()

        if (usuario.isNotBlank()) {
            sharedPrefManager.username = usuario
        }

        if (usuario.isBlank()) {
            binding.etUsername.setError("Este campo no puede estar vacío")
            valido = false
        }

        if (contraseña.isBlank()) {
            binding.etPassword.setError("Este campo no puede estar vacío")
            valido = false
        }
        return valido
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPrefManager = SharedPrefManager(requireContext())

        binding.btnLogIn.setOnClickListener {
            try {
                if (validarCamposLogin()) {
                    val usuario = binding.etUsername.text.toString()
                    val contraseña = binding.etPassword.text.toString()

                    sharedPrefManager.username = usuario

                    viewModel.validarRegistro(usuario, contraseña)
                        .observe(viewLifecycleOwner) { registro ->
                            if (registro != null) {
                                (activity as MainActivity).showBottomNavigationView()
                                it.findNavController().navigate(R.id.login_menu)
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "Usuario o contraseña incorrectos",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
            } catch (ex: Exception) {
                Toast.makeText(
                    requireContext(), "Error : ${ex.toString()}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        binding.tvRegistro.setOnClickListener {
            it.findNavController().navigate(R.id.login_registro)
        }
    }
}