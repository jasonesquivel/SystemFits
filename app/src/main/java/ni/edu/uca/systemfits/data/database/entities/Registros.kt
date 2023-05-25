package ni.edu.uca.systemfits.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat

@Entity(tableName = "tblregistros")
data class Registros(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val nombre: String,
    val apellido: String,
    val fechaNac: String,
    val genéro: String,
    val peso: Double,
    val altura: Double,
    val usuario: String,
    val contraseña: String
)