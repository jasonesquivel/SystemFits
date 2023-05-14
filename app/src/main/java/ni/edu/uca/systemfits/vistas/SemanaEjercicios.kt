package ni.edu.uca.systemfits.vistas

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import ni.edu.uca.systemfits.R
import ni.edu.uca.systemfits.databinding.FragmentSemanaEjerciciosBinding

class SemanaEjercicios : Fragment() {
    private lateinit var binding: FragmentSemanaEjerciciosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    private fun showCustomPopupAgregarGrupoMUsuclar() {
        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView =
            inflater.inflate(R.layout.fragment_dialog_input_agregar_grupo_muscular, null)

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSemanaEjerciciosBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAgregarGrupoMuscularLunes.setOnClickListener {
            showCustomPopupAgregarGrupoMUsuclar()
        }
        binding.btnAgregarGrupoMuscularMartes.setOnClickListener {
            showCustomPopupAgregarGrupoMUsuclar()
        }
        binding.btnAgregarGrupoMuscularMiercoles.setOnClickListener {
            showCustomPopupAgregarGrupoMUsuclar()
        }
        binding.btnAgregarGrupoMuscularJueves.setOnClickListener {
            showCustomPopupAgregarGrupoMUsuclar()
        }
        binding.btnAgregarGrupoMuscularViernes.setOnClickListener {
            showCustomPopupAgregarGrupoMUsuclar()
        }
        binding.btnAgregarGrupoMuscularSabado.setOnClickListener {
            showCustomPopupAgregarGrupoMUsuclar()
        }
        binding.btnAgregarGrupoMuscularDomingo.setOnClickListener {
            showCustomPopupAgregarGrupoMUsuclar()
        }
    }


}