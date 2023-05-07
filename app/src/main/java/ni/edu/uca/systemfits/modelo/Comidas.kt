package ni.edu.uca.systemfits.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblcomidas")
data class Comidas(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val comida: String,
    val calorias: Int,
)