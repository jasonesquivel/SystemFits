package ni.edu.uca.systemfits

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import ni.edu.uca.systemfits.databinding.FragmentEjerciciosBinding
import java.util.*


class Ejercicios : Fragment() {

private lateinit var binding: FragmentEjerciciosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    //ESTE ES PARA EL AGREGAR MEDIDA POPUP//
    private fun showCustomPopupAgregarMedida() {
        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.fragment_dialog_input_agregar_ejercicio, null)

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
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    binding=FragmentEjerciciosBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val calendar = Calendar.getInstance()
        val diaActual = calendar.get(Calendar.DAY_OF_WEEK)

        when (diaActual) {
            Calendar.MONDAY -> {

                binding.lunesRadioButton.isChecked = true
                binding.tvDiaSemana.setText("Lunes")
            }
            Calendar.TUESDAY -> {
                binding.martesRadioButton.isChecked = true
                binding.tvDiaSemana.setText("Martes")
            }
            Calendar.WEDNESDAY -> {
                binding.miercolesRadioButton.isChecked = true
                binding.tvDiaSemana.setText("Miercoles")
            }
            Calendar.THURSDAY -> {
                binding.juevesRadioButton.isChecked = true
                binding.tvDiaSemana.setText("Jueves")
            }

            Calendar.FRIDAY -> {
                binding.tvDiaSemana.setText("Viernes")
                binding.viernesRadioButton.isChecked = true
            } Calendar.SATURDAY -> {
            binding.sabadoRadioButton.isChecked = true
            binding.tvDiaSemana.setText("Sabado")
        }
            Calendar.SUNDAY -> {
                binding.domingoRadioButton.isChecked = true
                binding.tvDiaSemana.setText("Domingo")
            }


        }
        val showPopupButton = binding.btnAgregarEjercicio
        showPopupButton.setOnClickListener {
            showCustomPopupAgregarMedida()
        }

    }


}

