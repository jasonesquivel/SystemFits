package ni.edu.uca.systemfits.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblmedidas")
data class Medidas(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val musculo: String,
    val tamaño: Double,
)