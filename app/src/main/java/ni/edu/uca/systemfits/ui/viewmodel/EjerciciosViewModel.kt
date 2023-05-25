package ni.edu.uca.systemfits.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ni.edu.uca.systemfits.data.database.AppDatabase
import ni.edu.uca.systemfits.data.database.dao.EjerciciosDao
import ni.edu.uca.systemfits.data.database.entities.Ejercicios

class EjerciciosViewModel(application: Application) : AndroidViewModel(application) {

    private val EjerciciosDao: EjerciciosDao

    init {
        val database = AppDatabase.getInstance(application.applicationContext)
        EjerciciosDao = database.EjerciciosDao()
    }

    suspend fun insertar(Ejercicios: Ejercicios) = withContext(Dispatchers.IO) {
        EjerciciosDao.insertar(Ejercicios)
    }

    suspend fun actualizar(Ejercicios: Ejercicios) = withContext(Dispatchers.IO) {
        EjerciciosDao.actualizar(Ejercicios)
    }

    suspend fun eliminar(Ejercicios: Ejercicios) = withContext(Dispatchers.IO) {
        EjerciciosDao.eliminar(Ejercicios)
    }

    val todos: LiveData<List<Ejercicios>> = EjerciciosDao.obtenerTodos()
}