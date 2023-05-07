package ni.edu.uca.systemfits

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import android.widget.PopupWindow
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ni.edu.uca.systemfits.dao.MedidasAdapter
import ni.edu.uca.systemfits.modelo.Medidas
import ni.edu.uca.systemfits.databinding.FragmentDialogInputAgregarMedidaBinding
import ni.edu.uca.systemfits.databinding.FragmentDialogInputEditarMedidaBinding
import ni.edu.uca.systemfits.databinding.FragmentMedidasBinding
import ni.edu.uca.systemfits.modelo.MedidasViewModel

class Medidas : Fragment() {

    private lateinit var binding: FragmentMedidasBinding
    private lateinit var binding2: FragmentDialogInputAgregarMedidaBinding
    private lateinit var binding3: FragmentDialogInputEditarMedidaBinding
    private var medidaSeleccionada: Medidas? = null
    private lateinit var medidasAdapter: MedidasAdapter
    private val viewModel: MedidasViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    //ESTE ES PARA EL AGREGAR MEDIDA POPUP//
    private fun showCustomPopupAgregarMedida() {
        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.fragment_dialog_input_agregar_medida, null)

        binding2 = FragmentDialogInputAgregarMedidaBinding.bind(popupView)

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

        binding2.btnGuardarMedida.setOnClickListener {
            try {
                val musculo = binding2.etMusculo.text.toString()
                val medida = binding2.etMedida.text.toString().toInt()

                val medidas = Medidas(
                    musculo = musculo, tamaño = medida
                )

                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.insertar(medidas)
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


    //ESTE ES PARA EL EDITAR COMIDA POPUP//
    private fun showCustomPopupEditarMedida(medidaSeleccionada: Medidas) {
        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView1 = inflater.inflate(R.layout.fragment_dialog_input_editar_medida, null)

        binding3 = FragmentDialogInputEditarMedidaBinding.bind(popupView1)

        binding3.etNombreEdit.setText(medidaSeleccionada.musculo)
        binding3.etMedidaEdit.setText(medidaSeleccionada.tamaño.toString())

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

        binding3.btnEditarMedida.setOnClickListener {
            try {
                medidaSeleccionada?.let { Medidas ->
                    val nombre = binding3.etNombreEdit.text.toString()
                    val medida = binding3.etMedidaEdit.text.toString().toInt()

                    val medidaActualizada = Medidas(
                        id = Medidas.id,
                        musculo = nombre,
                        tamaño = medida
                    )
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.actualizar(medidaActualizada)
                    }
                    popupWindow1.dismiss()
                }
            } catch (ex: Exception) {
                Toast.makeText(
                    requireContext(), "Error : ${ex.toString()}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        binding3.btnEliminarMedida.setOnClickListener {
            try {
                AlertDialog.Builder(requireContext()).setTitle("Confirmación")
                    .setMessage("¿Estás seguro de eliminar este musculo?")
                    .setPositiveButton("Si") { _, _ ->
                        medidaSeleccionada?.let { Medida ->
                            CoroutineScope(Dispatchers.IO).launch {
                                viewModel.eliminar(Medida)
                            }
                            popupWindow1.dismiss()
                        }
                    }.setNegativeButton("Cancelar") { dialog, _ ->
                        dialog.dismiss()
                        popupWindow1.dismiss()
                    }
                    .create()
                    .show()
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
        binding = FragmentMedidasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        medidasAdapter = MedidasAdapter { Medidas ->
            medidaSeleccionada = Medidas
            showCustomPopupEditarMedida(medidaSeleccionada!!)
        }

        binding.recyclerViewMedidas.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = medidasAdapter
        }

        viewModel.todos.observe(viewLifecycleOwner, Observer { Medidas ->
            medidasAdapter.setMedidas(Medidas)
        })

        val showPopupButton = binding.buttonAgregarMedida
        showPopupButton.setOnClickListener {
            showCustomPopupAgregarMedida()
        }
    }
}
