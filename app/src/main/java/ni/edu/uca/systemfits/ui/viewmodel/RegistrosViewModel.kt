package ni.edu.uca.systemfits.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ni.edu.uca.systemfits.data.database.AppDatabase
import ni.edu.uca.systemfits.data.database.dao.RegistrosDao
import ni.edu.uca.systemfits.data.database.entities.Registros

class RegistrosViewModel(application: Application) : AndroidViewModel(application) {

    private val RegistrosDao: RegistrosDao

    init {
        val database = AppDatabase.getInstance(application.applicationContext)
        RegistrosDao = database.RegistrosDao()
    }

    suspend fun insertar(Registros: Registros) = withContext(Dispatchers.IO) {
        RegistrosDao.insertar(Registros)
    }

    suspend fun eliminar(Registros: Registros) = withContext(Dispatchers.IO) {
        RegistrosDao.eliminar(Registros)
    }

    suspend fun actualizarRegistro(Registros: Registros) {
        withContext(Dispatchers.IO) {
            RegistrosDao.actualizarRegistro(
                Registros.usuario,
                Registros.nombre,
                Registros.apellido,
                Registros.fechaNac,
                Registros.genéro,
                Registros.peso,
                Registros.altura,
                Registros.contraseña
            )
        }
    }

    fun validarRegistro(usuario: String, contraseña: String): LiveData<Registros?> {
        return RegistrosDao.validarRegistro(usuario, contraseña)
    }

    fun obtenerUsuario(usuario: String): LiveData<Registros> {
        return RegistrosDao.obtenerUsuarioPorNombre(usuario)
    }
}