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
import android.widget.PopupWindow
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ni.edu.uca.systemfits.dao.ComidasAdapter
import ni.edu.uca.systemfits.databinding.FragmentComidaBinding
import ni.edu.uca.systemfits.databinding.FragmentDialogInputAgregarComidaBinding
import ni.edu.uca.systemfits.databinding.FragmentDialogInputEditarComidaBinding
import ni.edu.uca.systemfits.modelo.Comidas
import ni.edu.uca.systemfits.modelo.ComidasViewModel

class Comida : Fragment() {

    private lateinit var binding: FragmentComidaBinding
    private lateinit var binding2: FragmentDialogInputAgregarComidaBinding
    private lateinit var binding3: FragmentDialogInputEditarComidaBinding
    private var comidaSeleccionada: Comidas? = null
    private lateinit var comidasAdapter: ComidasAdapter
    private val viewModel: ComidasViewModel by viewModels()

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

        binding2 = FragmentDialogInputAgregarComidaBinding.bind(popupView)

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

        binding2.btnGuardarComida.setOnClickListener {
            try {
                val comida = binding2.etComida.text.toString()
                val caloria = binding2.etCalorias.text.toString().toInt()

                val comidas = Comidas(
                    comida = comida, calorias = caloria
                )

                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.insertar(comidas)
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
    private fun showCustomPopupEditarComida(comidaSeleccionada: Comidas) {
        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView1 = inflater.inflate(R.layout.fragment_dialog_input_editar_comida, null)

        binding3 = FragmentDialogInputEditarComidaBinding.bind(popupView1)

        binding3.etComida.setText(comidaSeleccionada.comida)
        binding3.etCalorias.setText(comidaSeleccionada.calorias.toString())

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

        binding3.btnEditarComida.setOnClickListener {
            try {
                comidaSeleccionada?.let { Comidas ->
                    val comida = binding3.etComida.text.toString()
                    val caloria = binding3.etCalorias.text.toString().toInt()

                    val comidaActualizada = Comidas(
                        id = Comidas.id,
                        comida = comida,
                        calorias = caloria
                    )
                    CoroutineScope(Dispatchers.IO).launch {
                        viewModel.actualizar(comidaActualizada)
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

        binding3.btnEliminarComida.setOnClickListener {
            try {
                AlertDialog.Builder(requireContext()).setTitle("Confirmación")
                    .setMessage("¿Estás seguro de eliminar está comida?")
                    .setPositiveButton("Si") { _, _ ->
                        comidaSeleccionada?.let { Comida ->
                            CoroutineScope(Dispatchers.IO).launch {
                                viewModel.eliminar(Comida)
                            }
                            popupWindow1.dismiss()
                        }
                    }.setNegativeButton("Cancelar") { dialog, _ ->
                        dialog.dismiss()
                        popupWindow1.dismiss()
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentComidaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        comidasAdapter = ComidasAdapter { Comidas ->
            comidaSeleccionada = Comidas
            showCustomPopupEditarComida(comidaSeleccionada!!)
        }

        binding.recyclerViewComidas.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = comidasAdapter
        }

        viewModel.todos.observe(viewLifecycleOwner, Observer { Comidas ->
            comidasAdapter.setComidas(Comidas)
        })

        val showPopupButton = binding.buttonAgregarComida
        showPopupButton.setOnClickListener {
            showCustomPopupAgregarComida()
        }
    }
}