package ni.edu.uca.systemfits.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ni.edu.uca.systemfits.data.database.entities.Ejercicios

@Dao
interface EjerciciosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertar(Ejercicios: Ejercicios)

    @Update
    fun actualizar(Ejercicios: Ejercicios): Int

    @Delete
    fun eliminar(Ejercicios: Ejercicios): Int

    @Query("Select * From tblejercicios")
    fun obtenerTodos(): LiveData<List<Ejercicios>>
}