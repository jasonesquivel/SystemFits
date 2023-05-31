package ni.edu.uca.systemfits.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ni.edu.uca.systemfits.data.database.entities.SemanaEjercicios

@Dao
interface SemanaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertar(SemanaEjercicios: SemanaEjercicios): Long

    @Query("DELETE FROM tblsemanaejercicios WHERE dia = :dia")
    fun eliminarGrupoMuscular(dia: String)

    @Query("SELECT grupomuscular FROM tblsemanaejercicios WHERE dia = :dia")
    fun obtenerGrupoMuscular(dia: String): LiveData<String>

    @Query("UPDATE tblsemanaejercicios SET grupomuscular = :nuevoGrupoMuscular WHERE dia = :dia")
    fun actualizarGrupoMuscular(dia: String, nuevoGrupoMuscular: String)
}