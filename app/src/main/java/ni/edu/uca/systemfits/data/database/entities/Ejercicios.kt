package ni.edu.uca.systemfits.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblejercicios")
data class Ejercicios(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val repeticiones: Int,
    val series: Int
)