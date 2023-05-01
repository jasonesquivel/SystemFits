package ni.edu.uca.systemfits

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import ni.edu.uca.systemfits.databinding.ActivityMainBinding
import ni.edu.uca.systemfits.databinding.FragmentLoginBinding


class Login : Fragment() {

    lateinit var binding: FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)


        binding.btnLogIn.setOnClickListener {
            (activity as MainActivity).showBottomNavigationView()
            it.findNavController().navigate(R.id.login_menu)


        }

        binding.btnRegistro.setOnClickListener {
            it.findNavController().navigate(R.id.login_registro)


        }
    }


}