package ni.edu.uca.systemfits.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ni.edu.uca.systemfits.modelo.Medidas

@Dao
interface MedidasDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertar(Medidas: Medidas): Long

    @Update
    fun actualizar(Medidas: Medidas): Int

    @Delete
    fun eliminar(Medidas: Medidas): Int

    @Query("Select * From tblmedidas")
    fun obtenerTodos(): LiveData<List<Medidas>>
}