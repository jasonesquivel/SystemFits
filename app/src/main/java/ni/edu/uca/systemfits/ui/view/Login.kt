package ni.edu.uca.systemfits.ui.view

import android.content.Context
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_comida.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ni.edu.uca.systemfits.R
import ni.edu.uca.systemfits.data.database.entities.Comidas
import ni.edu.uca.systemfits.databinding.FragmentAutoresBinding
import ni.edu.uca.systemfits.databinding.FragmentDialogInputAgregarComidaBinding
import ni.edu.uca.systemfits.databinding.FragmentLoginBinding
import ni.edu.uca.systemfits.ui.sharedpreferences.SharedPrefUtil
import ni.edu.uca.systemfits.ui.viewmodel.RegistrosViewModel

class Login : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: RegistrosViewModel by viewModels()
    private lateinit var binding2: FragmentAutoresBinding

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

    private fun showCustomPopupAutores() {
        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.fragment_autores, null)

        val popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        val closeButton = popupView.findViewById<ImageView>(R.id.close_popup_button)
        closeButton.setOnClickListener {
            popupWindow.dismiss()
        }

        popupWindow.isOutsideTouchable = true
        popupWindow.isFocusable = true

        popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAutores.setOnClickListener {
            try {
                showCustomPopupAutores()
            } catch (ex: Exception) {
                Toast.makeText(
                    requireContext(), "Error : ${ex.toString()}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        binding.btnMostrarContraseA.setOnClickListener {
            val isPasswordVisible =
                binding.etPassword.transformationMethod == HideReturnsTransformationMethod.getInstance()
            if (isPasswordVisible) {
                binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.btnMostrarContraseA.text = "Mostrar"
            } else {
                binding.etPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                binding.btnMostrarContraseA.text = "Ocultar"
            }
        }

        binding.btnLogIn.setOnClickListener {
            try {
                if (validarCamposLogin()) {
                    val usuario = binding.etUsername.text.toString()
                    val contraseña = binding.etPassword.text.toString()

                    viewModel.validarRegistro(usuario, contraseña)
                        .observe(viewLifecycleOwner) { registro ->
                            if (registro != null) {
                                SharedPrefUtil.guardarUsuario(requireContext(), usuario)
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
