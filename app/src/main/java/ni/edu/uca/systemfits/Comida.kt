package ni.edu.uca.systemfits

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import ni.edu.uca.systemfits.databinding.FragmentComidaBinding


class Comida : Fragment() {

    private lateinit var binding:FragmentComidaBinding
            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentComidaBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)


        binding.btnEditarComida.setOnClickListener {
            val showPopUpCalorias =PopUpCalorias()
            showPopUpCalorias.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
        }


    }
}