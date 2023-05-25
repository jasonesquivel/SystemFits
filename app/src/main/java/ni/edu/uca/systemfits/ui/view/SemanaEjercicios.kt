package ni.edu.uca.systemfits.ui.view

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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ni.edu.uca.systemfits.R
import ni.edu.uca.systemfits.databinding.FragmentDialogInputAgregarGrupoMuscularBinding
import ni.edu.uca.systemfits.databinding.FragmentSemanaEjerciciosBinding
import ni.edu.uca.systemfits.data.database.entities.SemanaEjercicios
import ni.edu.uca.systemfits.ui.viewmodel.SemanaViewModel

class SemanaEjercicios : Fragment() {
    private lateinit var binding: FragmentSemanaEjerciciosBinding
    private lateinit var binding2: FragmentDialogInputAgregarGrupoMuscularBinding
    private val viewModel: SemanaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
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
                val dia = binding.tvLunes.text.toString()
                val grupomuscular = binding2.etGrupoMuscular.text.toString()

                val grupoMuscular = SemanaEjercicios(
                    dia = dia, grupomuscular = grupomuscular
                )

                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.insertar(grupoMuscular)
                }

                popupWindow.dismiss()

            } catch (ex: Exception) {
                Toast.makeText(
                    requireContext(), "Error : ${ex.toString()}",
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
                val dia = binding.tvMartes.text.toString()
                val grupomuscular = binding2.etGrupoMuscular.text.toString()

                val grupoMuscular = SemanaEjercicios(
                    dia = dia, grupomuscular = grupomuscular
                )

                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.insertar(grupoMuscular)
                }

                popupWindow.dismiss()

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
                val dia = binding.tvMiercoles.text.toString()
                val grupomuscular = binding2.etGrupoMuscular.text.toString()

                val grupoMuscular = SemanaEjercicios(
                    dia = dia, grupomuscular = grupomuscular
                )

                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.insertar(grupoMuscular)
                }

                popupWindow.dismiss()

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
                val dia = binding.tvJueves.text.toString()
                val grupomuscular = binding2.etGrupoMuscular.text.toString()

                val grupoMuscular = SemanaEjercicios(
                    dia = dia, grupomuscular = grupomuscular
                )

                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.insertar(grupoMuscular)
                }

                popupWindow.dismiss()

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
                val dia = binding.tvViernes.text.toString()
                val grupomuscular = binding2.etGrupoMuscular.text.toString()

                val grupoMuscular = SemanaEjercicios(
                    dia = dia, grupomuscular = grupomuscular
                )

                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.insertar(grupoMuscular)
                }

                popupWindow.dismiss()

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
                val dia = binding.tvSabado.text.toString()
                val grupomuscular = binding2.etGrupoMuscular.text.toString()

                val grupoMuscular = SemanaEjercicios(
                    dia = dia, grupomuscular = grupomuscular
                )

                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.insertar(grupoMuscular)
                }

                popupWindow.dismiss()

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
                val dia = binding.tvDomingo.text.toString()
                val grupomuscular = binding2.etGrupoMuscular.text.toString()

                val grupoMuscular = SemanaEjercicios(
                    dia = dia, grupomuscular = grupomuscular
                )

                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.insertar(grupoMuscular)
                }

                popupWindow.dismiss()

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
        binding.btnAgregarGrupoMuscularLunes.setOnClickListener {
            showCustomPopupAgregarGrupoMuscularLunes()
        }
        binding.btnAgregarGrupoMuscularMartes.setOnClickListener {
            showCustomPopupAgregarGrupoMuscularMartes()
        }
        binding.btnAgregarGrupoMuscularMiercoles.setOnClickListener {
            showCustomPopupAgregarGrupoMuscularMiercoles()
        }
        binding.btnAgregarGrupoMuscularJueves.setOnClickListener {
            showCustomPopupAgregarGrupoMuscularJueves()
        }
        binding.btnAgregarGrupoMuscularViernes.setOnClickListener {
            showCustomPopupAgregarGrupoMuscularViernes()
        }
        binding.btnAgregarGrupoMuscularSabado.setOnClickListener {
            showCustomPopupAgregarGrupoMuscularSabado()
        }
        binding.btnAgregarGrupoMuscularDomingo.setOnClickListener {
            showCustomPopupAgregarGrupoMuscularDomingo()
        }
    }
}