package ni.edu.uca.systemfits.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ni.edu.uca.systemfits.data.database.AppDatabase
import ni.edu.uca.systemfits.data.database.dao.SemanaDao
import ni.edu.uca.systemfits.data.database.entities.SemanaEjercicios

class SemanaViewModel(application: Application) : AndroidViewModel(application) {

    private val SemanaDao: SemanaDao

    init {
        val database = AppDatabase.getInstance(application.applicationContext)
        SemanaDao = database.SemanaDao()
    }

    suspend fun insertar(SemanaEjercicios: SemanaEjercicios) = withContext(Dispatchers.IO) {
        SemanaDao.insertar(SemanaEjercicios)
    }

    suspend fun actualizarGrupoMuscular(dia: String, grupoMuscular: String) =
        withContext(Dispatchers.IO) {
            SemanaDao.actualizarGrupoMuscular(dia, grupoMuscular)
        }

    suspend fun eliminar(SemanaEjercicios: SemanaEjercicios) = withContext(Dispatchers.IO) {
        SemanaDao.eliminar(SemanaEjercicios)
    }

    fun obtenerGrupoMuscular(dia: String): LiveData<String> {
        return SemanaDao.obtenerGrupoMuscular(dia)
    }
}