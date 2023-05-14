package ni.edu.uca.systemfits.vistas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import ni.edu.uca.systemfits.MainActivity
import ni.edu.uca.systemfits.R
import ni.edu.uca.systemfits.databinding.FragmentLoginBinding

class Login : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogIn.setOnClickListener {

            (activity as MainActivity).showBottomNavigationView()

            it.findNavController().navigate(R.id.login_menu)

        }
        binding.tvRegistro.setOnClickListener {
            it.findNavController().navigate(R.id.login_registro)

        }

    }
}