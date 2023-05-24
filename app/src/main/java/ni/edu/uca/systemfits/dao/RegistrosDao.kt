package ni.edu.uca.systemfits.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ni.edu.uca.systemfits.modelo.Registros

@Dao
interface RegistrosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertar(Registros: Registros): Long

    @Update
    fun actualizar(Registros: Registros): Int

    @Delete
    fun eliminar(Registros: Registros): Int

    @Query("Select * From tblregistros")
    fun obtenerTodos(): LiveData<List<Registros>>

    @Query("SELECT * FROM tblregistros WHERE usuario = :usuario AND contraseña = :contraseña")
    fun validarRegistro(usuario: String, contraseña: String): LiveData<Registros?>

    @Query("SELECT * FROM tblregistros WHERE nombre = :nombre")
    fun obtenerUsuarioPorNombre(nombre: String): LiveData<Registros>
}