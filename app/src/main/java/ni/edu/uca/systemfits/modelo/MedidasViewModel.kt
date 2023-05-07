package ni.edu.uca.systemfits.modelo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ni.edu.uca.systemfits.dao.AppDatabase
import ni.edu.uca.systemfits.dao.MedidasDao

class MedidasViewModel(application: Application) : AndroidViewModel(application) {

    private val MedidasDao: MedidasDao

    init {
        val database = AppDatabase.getInstance(application.applicationContext)
        MedidasDao = database.medidasDao()
    }

    suspend fun insertar(Medidas: Medidas) = withContext(Dispatchers.IO) {
        MedidasDao.insertar(Medidas)
    }

    suspend fun actualizar(Medidas: Medidas) = withContext(Dispatchers.IO) {
        MedidasDao.actualizar(Medidas)
    }

    suspend fun eliminar(Medidas: Medidas) = withContext(Dispatchers.IO) {
        MedidasDao.eliminar(Medidas)
    }

    val todos: LiveData<List<Medidas>> = MedidasDao.obtenerTodos()
}