package ni.edu.uca.systemfits.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ni.edu.uca.systemfits.data.database.entities.Registros

@Dao
interface RegistrosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertar(Registros: Registros): Long

    @Delete
    fun eliminar(Registros: Registros): Int

    @Query("UPDATE tblregistros SET nombre = :nombre, apellido = :apellido, fechaNac = :fechaNac, genéro = :genero, peso = :peso, altura = :altura, contraseña = :contraseña WHERE usuario = :usuario")
    fun actualizarRegistro(
        usuario: String,
        nombre: String,
        apellido: String,
        fechaNac: String,
        genero: String,
        peso: Double,
        altura: Double,
        contraseña: String
    )


    @Query("SELECT * FROM tblregistros WHERE usuario = :usuario AND contraseña = :contraseña")
    fun validarRegistro(usuario: String, contraseña: String): LiveData<Registros?>

    @Query("SELECT * FROM tblregistros WHERE usuario = :usuario")
    fun obtenerUsuarioPorNombre(usuario: String): LiveData<Registros>
}