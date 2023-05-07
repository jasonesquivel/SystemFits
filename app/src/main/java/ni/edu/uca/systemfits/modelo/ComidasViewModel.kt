package ni.edu.uca.systemfits.modelo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ni.edu.uca.systemfits.dao.AppDatabase
import ni.edu.uca.systemfits.dao.ComidasDao

class ComidasViewModel(application: Application) : AndroidViewModel(application) {

    private val ComidasDao: ComidasDao

    init {
        val database = AppDatabase.getInstance(application.applicationContext)
        ComidasDao = database.ComidasDao()
    }

    suspend fun insertar(Comidas: Comidas) = withContext(Dispatchers.IO) {
        ComidasDao.insertar(Comidas)
    }

    suspend fun actualizar(Comidas: Comidas) = withContext(Dispatchers.IO) {
        ComidasDao.actualizar(Comidas)
    }

    suspend fun eliminar(Comidas: Comidas) = withContext(Dispatchers.IO) {
        ComidasDao.eliminar(Comidas)
    }

    val todos: LiveData<List<Comidas>> = ComidasDao.obtenerTodos()
}