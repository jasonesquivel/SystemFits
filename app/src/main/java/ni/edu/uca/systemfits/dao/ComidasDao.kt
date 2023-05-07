package ni.edu.uca.systemfits.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ni.edu.uca.systemfits.modelo.Comidas

@Dao
interface ComidasDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertar(Comidas: Comidas): Long

    @Update
    fun actualizar(Comidas: Comidas): Int

    @Delete
    fun eliminar(Comidas: Comidas): Int

    @Query("Select * From tblcomidas")
    fun obtenerTodos(): LiveData<List<Comidas>>
}