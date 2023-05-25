package ni.edu.uca.systemfits.ui.view

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ni.edu.uca.systemfits.R
import ni.edu.uca.systemfits.ui.adapter.EjerciciosAdapter
import ni.edu.uca.systemfits.databinding.FragmentDialogInputAgregarEjercicioBinding
import ni.edu.uca.systemfits.databinding.FragmentEjerciciosBinding
import ni.edu.uca.systemfits.data.database.entities.Ejercicios
import ni.edu.uca.systemfits.databinding.FragmentDialogInputEditarEjercicioBinding
import ni.edu.uca.systemfits.ui.viewmodel.EjerciciosViewModel
import ni.edu.uca.systemfits.ui.viewmodel.SemanaViewModel
import java.util.*

class Ejercicios : Fragment() {

    private lateinit var binding: FragmentEjerciciosBinding
    private val viewModel: EjerciciosViewModel by viewModels()
    private val viewModel2: SemanaViewModel by viewModels()
    private lateinit var binding2: FragmentDialogInputAgregarEjercicioBinding
    private lateinit var binding3: FragmentDialogInputEditarEjercicioBinding
    private lateinit var EjerciciosAdapter: EjerciciosAdapter
    private var ejercicioSeleccionado: Ejercicios? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    private fun showCustomPopupEditarEjercicios(ejercicioSeleccionado: Ejercicios) {
        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView1 = inflater.inflate(R.layout.fragment_dialog_input_editar_ejercicio, null)

        binding3 = FragmentDialogInputEditarEjercicioBinding.bind(popupView1)

        binding3.etNombreEjercicioEditable.setText(ejercicioSeleccionado.nombre)
        binding3.etRepeticionesEditable.setText(ejercicioSeleccionado.repeticiones.toString())
        binding3.etSeriesEditable.setText(ejercicioSeleccionado.series.toString())

        val popupWindow = PopupWindow(
            popupView1,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        val closeButton = popupView1.findViewById<ImageView>(R.id.close_popup_button)
        closeButton.setOnClickListener {
            popupWindow.dismiss()
        }

        popupWindow.isOutsideTouchable = true
        popupWindow.isFocusable = true

        popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)

        binding3.btnEditarEjercicio.setOnClickListener {
            try {
                if (validarCamposEEjercicio()) {
                    this.ejercicioSeleccionado?.let { Ejercicios ->
                        val nombre = binding3.etNombreEjercicioEditable.text.toString()
                        val repeticiones = binding3.etRepeticionesEditable.text.toString().toInt()
                        val series = binding3.etSeriesEditable.text.toString().toInt()

                        val ejercicioActualizado = Ejercicios(
                            id = Ejercicios.id,
                            nombre = nombre,
                            repeticiones = repeticiones,
                            series = series
                        )

                        CoroutineScope(Dispatchers.IO).launch {
                            viewModel.actualizar(ejercicioActualizado)
                        }
                        popupWindow.dismiss()
                    }
                }
            } catch (ex: Exception) {
                Toast.makeText(
                    requireContext(), "Error : ${ex.toString()}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        binding3.btnEliminarEjercicio.setOnClickListener {
            try {
                AlertDialog.Builder(requireContext()).setTitle("Confirmación")
                    .setMessage("¿Estás seguro de eliminar está comida?")
                    .setPositiveButton("Si") { _, _ ->
                        ejercicioSeleccionado?.let { Ejercicios ->
                            CoroutineScope(Dispatchers.IO).launch {
                                viewModel.eliminar(Ejercicios)
                            }
                            popupWindow.dismiss()
                        }
                    }.setNegativeButton("Cancelar") { dialog, _ ->
                        dialog.dismiss()
                        popupWindow.dismiss()
                    }.create()
                    .show()
            } catch (ex: Exception) {
                Toast.makeText(
                    requireContext(), "Error : ${ex.toString()}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun showCustomPopupAgregarEjercicios() {
        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.fragment_dialog_input_agregar_ejercicio, null)

        binding2 = FragmentDialogInputAgregarEjercicioBinding.bind(popupView)

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

        binding2.btnGuardarEjercicio.setOnClickListener {
            try {
                if (validarCamposGEjercicio()) {
                    val nombre = binding2.etNombreEjercicio.text.toString()
                    val repeticiones = binding2.etRepeticiones.text.toString().toInt()
                    val series = binding2.etSeries.text.toString().toInt()

                    val ejercicio = Ejercicios(
                        nombre = nombre, repeticiones = repeticiones, series = series
                    )

                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.insertar(ejercicio)
                    }
                    popupWindow.dismiss()
                }
            } catch (ex: Exception) {
                Toast.makeText(
                    requireContext(), "Error : ${ex.toString()}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

    private fun validarCamposEEjercicio(): Boolean {
        var valido = true

        val nombreEditable = binding3.etNombreEjercicioEditable.text.toString()
        val repeticionesEditable = binding3.etRepeticionesEditable.text.toString()
        val seriesEditable = binding3.etSeriesEditable.text.toString()

        if (nombreEditable.isBlank()) {
            binding3.etNombreEjercicioEditable.setError("Este campo no puede estar vacío")
            valido = false
        }

        if (repeticionesEditable.isBlank()) {
            binding3.etRepeticionesEditable.setError("Este campo no puede estar vacío")
            valido = false
        }
        if (seriesEditable.isBlank()) {
            binding3.etSeriesEditable.setError("Este campo no puede estar vacío")
            valido = false
        }
        return valido
    }

    private fun validarCamposGEjercicio(): Boolean {
        var valido = true

        val nombre = binding2.etNombreEjercicio.text.toString()
        val repeticiones = binding2.etRepeticiones.text.toString()
        val series = binding2.etSeries.text.toString()

        if (nombre.isBlank()) {
            binding2.etNombreEjercicio.setError("Este campo no puede estar vacío")
            valido = false
        }

        if (repeticiones.isBlank()) {
            binding2.etRepeticiones.setError("Este campo no puede estar vacío")
            valido = false
        }

        if (series.isBlank()) {
            binding2.etSeries.setError("Este campo no puede estar vacío")
            valido = false
        }
        return valido
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEjerciciosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        EjerciciosAdapter = EjerciciosAdapter { Ejercicios ->
            ejercicioSeleccionado = Ejercicios
            showCustomPopupEditarEjercicios(ejercicioSeleccionado!!)
        }

        binding.recyclerViewEjercicios.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = EjerciciosAdapter
        }

        val diaTextView = binding.tvDiayGrupoMuscular
        val grupoMuscularTextView = binding.tvGrupoMuscular

        val calendar = Calendar.getInstance()
        val diaActual = calendar.get(Calendar.DAY_OF_WEEK)

        when (diaActual) {
            Calendar.MONDAY -> {
                binding.lunesRadioButton.isChecked = true
                diaTextView.text = "Lunes"
            }
            Calendar.TUESDAY -> {
                binding.martesRadioButton.isChecked = true
                diaTextView.text = "Martes"
            }
            Calendar.WEDNESDAY -> {
                binding.miercolesRadioButton.isChecked = true
                diaTextView.text = "Miércoles"
            }
            Calendar.THURSDAY -> {
                binding.juevesRadioButton.isChecked = true
                diaTextView.text = "Jueves"
            }
            Calendar.FRIDAY -> {
                binding.viernesRadioButton.isChecked = true
                diaTextView.text = "Viernes"
            }
            Calendar.SATURDAY -> {
                binding.sabadoRadioButton.isChecked = true
                diaTextView.text = "Sábado"
            }
            Calendar.SUNDAY -> {
                binding.domingoRadioButton.isChecked = true
                diaTextView.text = "Domingo"
            }
        }

        viewModel2.obtenerGrupoMuscular(diaTextView.text.toString())
            .observe(viewLifecycleOwner, Observer { grupoMuscular ->
                grupoMuscularTextView.text = grupoMuscular
            })

        viewModel.todos.observe(viewLifecycleOwner, Observer { Ejercicios ->
            EjerciciosAdapter.setEjercicios(Ejercicios)
        })
        val showPopupButton = binding.btnAgregarEjercicio
        showPopupButton.setOnClickListener {
            showCustomPopupAgregarEjercicios()
        }

        binding.btnEditarSemana.setOnClickListener {
            val semanaEjercicios = SemanaEjercicios()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, semanaEjercicios)
                .addToBackStack(null)
                .commit()
        }
    }
}

