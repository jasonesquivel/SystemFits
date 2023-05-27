package ni.edu.uca.systemfits.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ni.edu.uca.systemfits.data.database.AppDatabase
import ni.edu.uca.systemfits.data.database.dao.ComidasDao
import ni.edu.uca.systemfits.data.database.entities.Comidas

class ComidasViewModel(application: Application) : AndroidViewModel(application) {

    private val ComidasDao: ComidasDao
    val todos: LiveData<List<Comidas>>

    init {
        val database = AppDatabase.getInstance(application.applicationContext)
        ComidasDao = database.ComidasDao()
        todos = ComidasDao.obtenerTodos()
    }

    fun insertar(comida: Comidas) {
        viewModelScope.launch(Dispatchers.IO) {
            ComidasDao.insertar(comida)
        }
    }

    fun actualizar(comida: Comidas) {
        viewModelScope.launch(Dispatchers.IO) {
            ComidasDao.actualizar(comida)
        }
    }

    fun eliminar(comida: Comidas) {
        viewModelScope.launch(Dispatchers.IO) {
            ComidasDao.eliminar(comida)
        }
    }

    fun getTotalCaloriasConsumidas(): LiveData<Int> {
        val totalCalorias = MutableLiveData<Int>()
        viewModelScope.launch(Dispatchers.IO) {
            val calorias = ComidasDao.obtenerTotalCalorias()
            withContext(Dispatchers.Main) {
                totalCalorias.value = calorias
            }
        }
        return totalCalorias
    }
}
