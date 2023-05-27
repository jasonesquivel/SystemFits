package ni.edu.uca.systemfits.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ni.edu.uca.systemfits.data.database.entities.SemanaEjercicios

@Dao
interface SemanaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertar(SemanaEjercicios: SemanaEjercicios): Long

    @Update
    fun actualizar(SemanaEjercicios: SemanaEjercicios): Int

    @Delete
    fun eliminar(SemanaEjercicios: SemanaEjercicios): Int

    @Query("SELECT grupomuscular FROM tblsemanaejercicios WHERE dia = :dia")
    fun obtenerGrupoMuscular(dia: String): LiveData<String>

    @Query("UPDATE tblsemanaejercicios SET grupomuscular = :nuevoGrupoMuscular WHERE dia = :dia")
    fun actualizarGrupoMuscular(dia: String, nuevoGrupoMuscular: String)

}