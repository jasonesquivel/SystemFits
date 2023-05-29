package ni.edu.uca.systemfits.ui.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ni.edu.uca.systemfits.ui.adapter.RecetasAdapter
import ni.edu.uca.systemfits.databinding.FragmentRecetasBinding
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ni.edu.uca.systemfits.data.model.Recetas

class Recetas : Fragment() {
    private lateinit var binding: FragmentRecetasBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var recetasAdapter: RecetasAdapter
    private lateinit var searchView: SearchView

    private var allRecetas: List<Recetas> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecetasBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerView
        searchView = binding.search

        return binding.root
    }

    //Llamado de las funciones
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        procesarDatos()
        setupSearchView()
    }

    //Configuracion del RCV recetas
    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recetasAdapter = RecetasAdapter(emptyList())
        recyclerView.adapter = recetasAdapter
    }

    //Configuracion de la barra de busqueda
    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterRecetas(newText)
                return true
            }
        })
    }

    //Filtro de las recetas
    fun filterRecetas(query: String) {
        val filteredRecetas = mutableListOf<Recetas>()

        for (receta in allRecetas) {
            if (receta.nombre.contains(query, true) || receta.descripcion.contains(query, true)) {
                filteredRecetas.add(receta)
            }
        }

        recetasAdapter.setData(filteredRecetas)
    }

    //Servicio para el consumo de la API
    interface ApiService {
        /*Debe añadir esta carpeta dentro de XAMPP/htdocs para poder acceder a la api*/
        @GET("SystemFitsApi/recetas.php")
        suspend fun procesarDatos(): ResponseBody
    }

    //Configuracion del cliente Retrofit
    object RetrofitClient {
        private const val BASE_URL = "http://192.168.1.29:80/"

        private val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService: ApiService = retrofit.create(ApiService::class.java)
    }

    //Procesamiento de los datos con Json
    private fun procesarDatos() {
        CoroutineScope(Dispatchers.IO).launch {
            var response: ResponseBody? = null
            try {
                response = RetrofitClient.apiService.procesarDatos()
                val jsonResponse = response.string()
                val jsonArray = JSONArray(jsonResponse)
                val recetas = mutableListOf<Recetas>()
                for (i in 0 until jsonArray.length()) {
                    val objeto = jsonArray.getJSONObject(i)
                    val nombre = objeto.getString("nombre")
                    val descripcion = objeto.getString("descripcion")
                    val calorias = objeto.getString("calorias")

                    val receta = Recetas(nombre, descripcion, calorias)
                    recetas.add(receta)
                }

                withContext(Dispatchers.Main) {
                    allRecetas = recetas
                    recetasAdapter.setData(recetas)
                }
            } catch (e: Exception) {
                Log.e("TAG", "Error al realizar la solicitud HTTP", e)
            } finally {
                response?.close()
            }
        }
    }
}