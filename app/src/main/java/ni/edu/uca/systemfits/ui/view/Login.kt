package ni.edu.uca.systemfits.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import ni.edu.uca.systemfits.R
import ni.edu.uca.systemfits.databinding.FragmentLoginBinding
import ni.edu.uca.systemfits.ui.viewmodel.RegistrosViewModel

class Login : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: RegistrosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun validarCamposLogin(): Boolean {
        val usuario = binding.etUsername.text.toString().trim()
        val contraseña = binding.etPassword.text.toString().trim()

        if (usuario.isBlank()) {
            binding.etUsername.error = "Este campo no puede estar vacío"
            return false
        }

        if (contraseña.isBlank()) {
            binding.etPassword.error = "Este campo no puede estar vacío"
            return false
        }
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogIn.setOnClickListener {
            try {
                if (validarCamposLogin()) {
                    val usuario = binding.etUsername.text.toString()
                    val contraseña = binding.etPassword.text.toString()

                    viewModel.validarRegistro(usuario, contraseña)
                        .observe(viewLifecycleOwner) { registro ->
                            if (registro != null) {
                                (activity as MainActivity).showBottomNavigationView()
                                it.findNavController().navigate(R.id.login_menu)
                                val bundle = bundleOf("disableBackToLogin" to true)
                                findNavController().navigate(R.id.menuPrincipal, bundle)
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
