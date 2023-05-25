package ni.edu.uca.systemfits.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblsemanaejercicios")
data class SemanaEjercicios(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val dia: String,
    val grupomuscular: String
)