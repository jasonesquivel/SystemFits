package ni.edu.uca.systemfits.ui.view

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
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_semana_ejercicios.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ni.edu.uca.systemfits.R
import ni.edu.uca.systemfits.databinding.FragmentDialogInputAgregarGrupoMuscularBinding
import ni.edu.uca.systemfits.databinding.FragmentSemanaEjerciciosBinding
import ni.edu.uca.systemfits.data.database.entities.SemanaEjercicios
import ni.edu.uca.systemfits.databinding.FragmentInputDialogEditarSemanaEjerciciosBinding
import ni.edu.uca.systemfits.ui.viewmodel.SemanaViewModel

class SemanaEjercicios : Fragment() {
    private lateinit var binding: FragmentSemanaEjerciciosBinding
    private lateinit var binding2: FragmentDialogInputAgregarGrupoMuscularBinding
    private lateinit var binding3: FragmentInputDialogEditarSemanaEjerciciosBinding
    private val viewModel: SemanaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    private fun validarCampos(): Boolean {
        var valido = true

        val grupomuscular = binding2.etGrupoMuscular.text.toString()

        if (grupomuscular.isBlank()) {
            Toast.makeText(
                requireContext(), "Debes ingresar el grupo muscular",
                Toast.LENGTH_LONG
            ).show()
            valido = false
        }

        return valido
    }

    private fun showCustomPopupAgregarGrupoMuscularLunes() {
        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView =
            inflater.inflate(R.layout.fragment_dialog_input_agregar_grupo_muscular, null)
        binding2 = FragmentDialogInputAgregarGrupoMuscularBinding.bind(popupView)

        val popupWindow = PopupWindow(
            popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )

        val closeButton = popupView.findViewById<ImageView>(R.id.close_popup_button)
        closeButton.setOnClickListener {
            popupWindow.dismiss()
        }

        popupWindow.isOutsideTouchable = true
        popupWindow.isFocusable = true

        popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)

        binding2.btnGuardarSemana.setOnClickListener {
            try {
                if (validarCampos()) {
                    val dia = binding.tvLunes.text.toString()
                    val grupomuscular = binding2.etGrupoMuscular.text.toString()

                    val grupoMuscular = SemanaEjercicios(
                        dia = dia, grupomuscular = grupomuscular
                    )

                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.insertar(grupoMuscular)
                    }

                    popupWindow.dismiss()
                    val grupoMuscularLunes = binding.tvGrupoMuscularLunes
                    viewModel.obtenerGrupoMuscular(tvLunes.text.toString())
                        .observe(viewLifecycleOwner, Observer { grupoMuscular ->
                            grupoMuscularLunes.text = grupoMuscular
                        })
                }
            } catch (ex: Exception) {
                Toast.makeText(
                    requireContext(), "Error : ${ex.toString()}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun showCustomPopupEditarGrupoMuscularLunes() {
        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView =
            inflater.inflate(R.layout.fragment_input_dialog_editar_semana_ejercicios, null)

        binding3 = FragmentInputDialogEditarSemanaEjerciciosBinding.bind(popupView)


        binding3.etGrupoMuscularEditable.setText(tvGrupoMuscularLunes.text.toString())


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

        binding3.btnEditarGrupoMuscular.setOnClickListener {
            try {
                if (validarCampos()) {
                    val diaLunes = binding.tvLunes.text.toString()
                    val grupomuscularLunes = binding3.etGrupoMuscularEditable.text.toString()

                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.actualizarGrupoMuscular(diaLunes, grupomuscularLunes)
                    }

                    popupWindow.dismiss()
                    val grupoMuscularLunes = binding.tvGrupoMuscularLunes
                    viewModel.obtenerGrupoMuscular(tvLunes.text.toString())
                        .observe(viewLifecycleOwner, Observer { grupoMuscular ->
                            grupoMuscularLunes.text = grupoMuscular
                        })
                }
            } catch (ex: Exception) {
                Toast.makeText(
                    requireContext(), "Error: ${ex.toString()}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun showCustomPopupAgregarGrupoMuscularMartes() {
        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView =
            inflater.inflate(R.layout.fragment_dialog_input_agregar_grupo_muscular, null)
        binding2 = FragmentDialogInputAgregarGrupoMuscularBinding.bind(popupView)

        val popupWindow = PopupWindow(
            popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )

        val closeButton = popupView.findViewById<ImageView>(R.id.close_popup_button)
        closeButton.setOnClickListener {
            popupWindow.dismiss()
        }

        popupWindow.isOutsideTouchable = true
        popupWindow.isFocusable = true

        popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)

        binding2.btnGuardarSemana.setOnClickListener {
            try {
                if (validarCampos()) {
                    val dia = binding.tvMartes.text.toString()
                    val grupomuscular = binding2.etGrupoMuscular.text.toString()

                    val grupoMuscular = SemanaEjercicios(
                        dia = dia, grupomuscular = grupomuscular
                    )

                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.insertar(grupoMuscular)
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

    private fun showCustomPopupAgregarGrupoMuscularMiercoles() {
        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView =
            inflater.inflate(R.layout.fragment_dialog_input_agregar_grupo_muscular, null)
        binding2 = FragmentDialogInputAgregarGrupoMuscularBinding.bind(popupView)

        val popupWindow = PopupWindow(
            popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )

        val closeButton = popupView.findViewById<ImageView>(R.id.close_popup_button)
        closeButton.setOnClickListener {
            popupWindow.dismiss()
        }

        popupWindow.isOutsideTouchable = true
        popupWindow.isFocusable = true

        popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)

        binding2.btnGuardarSemana.setOnClickListener {
            try {
                if (validarCampos()) {
                    val dia = binding.tvMiercoles.text.toString()
                    val grupomuscular = binding2.etGrupoMuscular.text.toString()

                    val grupoMuscular = SemanaEjercicios(
                        dia = dia, grupomuscular = grupomuscular
                    )

                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.insertar(grupoMuscular)
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

    private fun showCustomPopupAgregarGrupoMuscularJueves() {
        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView =
            inflater.inflate(R.layout.fragment_dialog_input_agregar_grupo_muscular, null)
        binding2 = FragmentDialogInputAgregarGrupoMuscularBinding.bind(popupView)

        val popupWindow = PopupWindow(
            popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )

        val closeButton = popupView.findViewById<ImageView>(R.id.close_popup_button)
        closeButton.setOnClickListener {
            popupWindow.dismiss()
        }

        popupWindow.isOutsideTouchable = true
        popupWindow.isFocusable = true

        popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)

        binding2.btnGuardarSemana.setOnClickListener {
            try {
                if (validarCampos()) {
                    val dia = binding.tvJueves.text.toString()
                    val grupomuscular = binding2.etGrupoMuscular.text.toString()

                    val grupoMuscular = SemanaEjercicios(
                        dia = dia, grupomuscular = grupomuscular
                    )

                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.insertar(grupoMuscular)
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

    private fun showCustomPopupAgregarGrupoMuscularViernes() {
        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView =
            inflater.inflate(R.layout.fragment_dialog_input_agregar_grupo_muscular, null)
        binding2 = FragmentDialogInputAgregarGrupoMuscularBinding.bind(popupView)

        val popupWindow = PopupWindow(
            popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )

        val closeButton = popupView.findViewById<ImageView>(R.id.close_popup_button)
        closeButton.setOnClickListener {
            popupWindow.dismiss()
        }

        popupWindow.isOutsideTouchable = true
        popupWindow.isFocusable = true

        popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)

        binding2.btnGuardarSemana.setOnClickListener {
            try {
                if (validarCampos()) {
                    val dia = binding.tvViernes.text.toString()
                    val grupomuscular = binding2.etGrupoMuscular.text.toString()

                    val grupoMuscular = SemanaEjercicios(
                        dia = dia, grupomuscular = grupomuscular
                    )

                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.insertar(grupoMuscular)
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

    private fun showCustomPopupAgregarGrupoMuscularSabado() {
        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView =
            inflater.inflate(R.layout.fragment_dialog_input_agregar_grupo_muscular, null)
        binding2 = FragmentDialogInputAgregarGrupoMuscularBinding.bind(popupView)

        val popupWindow = PopupWindow(
            popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )

        val closeButton = popupView.findViewById<ImageView>(R.id.close_popup_button)
        closeButton.setOnClickListener {
            popupWindow.dismiss()
        }

        popupWindow.isOutsideTouchable = true
        popupWindow.isFocusable = true

        popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)

        binding2.btnGuardarSemana.setOnClickListener {
            try {
                if (validarCampos()) {
                    val dia = binding.tvSabado.text.toString()
                    val grupomuscular = binding2.etGrupoMuscular.text.toString()

                    val grupoMuscular = SemanaEjercicios(
                        dia = dia, grupomuscular = grupomuscular
                    )

                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.insertar(grupoMuscular)
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

    private fun showCustomPopupAgregarGrupoMuscularDomingo() {
        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView =
            inflater.inflate(R.layout.fragment_dialog_input_agregar_grupo_muscular, null)
        binding2 = FragmentDialogInputAgregarGrupoMuscularBinding.bind(popupView)

        val popupWindow = PopupWindow(
            popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )

        val closeButton = popupView.findViewById<ImageView>(R.id.close_popup_button)
        closeButton.setOnClickListener {
            popupWindow.dismiss()
        }

        popupWindow.isOutsideTouchable = true
        popupWindow.isFocusable = true

        popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)

        binding2.btnGuardarSemana.setOnClickListener {
            try {
                if (validarCampos()) {
                    val dia = binding.tvDomingo.text.toString()
                    val grupomuscular = binding2.etGrupoMuscular.text.toString()

                    val grupoMuscular = SemanaEjercicios(
                        dia = dia, grupomuscular = grupomuscular
                    )

                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.insertar(grupoMuscular)
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSemanaEjerciciosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //cargar ViewModels para los grupos musculares//
        //LUNES//
        val grupoMuscularLunes = binding.tvGrupoMuscularLunes
        viewModel.obtenerGrupoMuscular(tvLunes.text.toString())
            .observe(viewLifecycleOwner, Observer { grupoMuscular ->
                grupoMuscularLunes.text = grupoMuscular
            })
        //MARTES//
        val grupoMuscularMartes = binding.tvGrupoMuscularMartes
        viewModel.obtenerGrupoMuscular(tvMartes.text.toString())
            .observe(viewLifecycleOwner, Observer { grupoMuscular ->
                grupoMuscularMartes.text = grupoMuscular
            })
        //MIERCOLES//
        val grupoMuscularMiercoles = binding.tvGrupoMuscularMiercoles
        viewModel.obtenerGrupoMuscular(tvMiercoles.text.toString())
            .observe(viewLifecycleOwner, Observer { grupoMuscular ->
                grupoMuscularMiercoles.text = grupoMuscular
            })
        //JUEVES//
        val grupoMuscularJueves = binding.tvGrupoMuscularJueves
        viewModel.obtenerGrupoMuscular(tvJueves.text.toString())
            .observe(viewLifecycleOwner, Observer { grupoMuscular ->
                grupoMuscularJueves.text = grupoMuscular
            })
        //VIERNES//
        val grupoMuscularViernes = binding.tvGrupoMuscularViernes
        viewModel.obtenerGrupoMuscular(tvViernes.text.toString())
            .observe(viewLifecycleOwner, Observer { grupoMuscular ->
                grupoMuscularViernes.text = grupoMuscular
            })
        //SABADO//
        val grupoMuscularSabado = binding.tvGrupoMuscularSabado
        viewModel.obtenerGrupoMuscular(tvSabado.text.toString())
            .observe(viewLifecycleOwner, Observer { grupoMuscular ->
                grupoMuscularSabado.text = grupoMuscular
            })
        //DOMINGO//
        val grupoMuscularDomingo = binding.tvGrupoMuscularDomingo
        viewModel.obtenerGrupoMuscular(tvDomingo.text.toString())
            .observe(viewLifecycleOwner, Observer { grupoMuscular ->
                grupoMuscularDomingo.text = grupoMuscular
            })

        binding.tvGrupoMuscularLunes.setOnClickListener {
            try {
                showCustomPopupEditarGrupoMuscularLunes()
            } catch (ex: Exception) {
                Toast.makeText(
                    requireContext(), "Error : ${ex.toString()}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        binding.btnAgregarGrupoMuscularLunes.setOnClickListener {
            try {
                showCustomPopupAgregarGrupoMuscularLunes()
            } catch (ex: Exception) {
                Toast.makeText(
                    requireContext(), "Error : ${ex.toString()}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        binding.btnAgregarGrupoMuscularMartes.setOnClickListener {
            try {
                showCustomPopupAgregarGrupoMuscularMartes()
            } catch (ex: Exception) {
                Toast.makeText(
                    requireContext(), "Error : ${ex.toString()}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        binding.btnAgregarGrupoMuscularMiercoles.setOnClickListener {
            try {
                showCustomPopupAgregarGrupoMuscularMiercoles()
            } catch (ex: Exception) {
                Toast.makeText(
                    requireContext(), "Error : ${ex.toString()}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        binding.btnAgregarGrupoMuscularJueves.setOnClickListener {
            try {
                showCustomPopupAgregarGrupoMuscularJueves()
            } catch (ex: Exception) {
                Toast.makeText(
                    requireContext(), "Error : ${ex.toString()}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        binding.btnAgregarGrupoMuscularViernes.setOnClickListener {
            try {
                showCustomPopupAgregarGrupoMuscularViernes()
            } catch (ex: Exception) {
                Toast.makeText(
                    requireContext(), "Error : ${ex.toString()}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        binding.btnAgregarGrupoMuscularSabado.setOnClickListener {
            try {
                showCustomPopupAgregarGrupoMuscularSabado()
            } catch (ex: Exception) {
                Toast.makeText(
                    requireContext(), "Error : ${ex.toString()}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        binding.btnAgregarGrupoMuscularDomingo.setOnClickListener {
            try {
                showCustomPopupAgregarGrupoMuscularDomingo()
            } catch (ex: Exception) {
                Toast.makeText(
                    requireContext(), "Error : ${ex.toString()}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}