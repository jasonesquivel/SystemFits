package ni.edu.uca.systemfits.vistas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import ni.edu.uca.systemfits.MainActivity
import ni.edu.uca.systemfits.R
import ni.edu.uca.systemfits.databinding.FragmentRegistroBinding


class Registro : Fragment() {

private lateinit var binding: FragmentRegistroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentRegistroBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)


        binding.btnRegistro.setOnClickListener {
            (activity as MainActivity).showBottomNavigationView()
            it.findNavController().navigate(R.id.registro_menuPrincipal)


        }
    }
}