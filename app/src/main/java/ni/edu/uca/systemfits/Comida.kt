package ni.edu.uca.systemfits

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import ni.edu.uca.systemfits.databinding.FragmentComidaBinding

class Comida : Fragment() {

    private lateinit var binding: FragmentComidaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    //ESTE ES PARA EL AGREGAR COMIDA POPUP//
    private fun showCustomPopupAgregarComida() {
        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.fragment_dialog_input_agregar_comida, null)

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

    //ESTE ES PARA EL EDITAR COMIDA POPUP//
    private fun showCustomPopupEditarComida() {
        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView1 = inflater.inflate(R.layout.fragment_dialog_input_editar_comida, null)

        val popupWindow1 = PopupWindow(
            popupView1,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        val closeButton = popupView1.findViewById<ImageView>(R.id.close_popup_button)
        closeButton.setOnClickListener {
            popupWindow1.dismiss()
        }

        popupWindow1.isOutsideTouchable = true
        popupWindow1.isFocusable = true

        popupWindow1.showAtLocation(binding.root, Gravity.CENTER, 0, 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentComidaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val showPopupButton = binding.buttonAgregarComida
        showPopupButton.setOnClickListener {
            showCustomPopupAgregarComida()
        }
        val showPopupButton1 = binding.buttonEditarComida
        showPopupButton1.setOnClickListener {
            showCustomPopupEditarComida()
        }
    }
}