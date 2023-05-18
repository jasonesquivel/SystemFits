package ni.edu.uca.systemfits.modelo

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
    val peso: Int,
    val altura: Int,
    val usuario: String,
    val contraseña: String
)