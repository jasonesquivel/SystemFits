package ni.edu.uca.systemfits.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room
import ni.edu.uca.systemfits.data.database.dao.ComidasDao
import ni.edu.uca.systemfits.data.database.dao.MedidasDao
import ni.edu.uca.systemfits.data.database.dao.RegistrosDao
import ni.edu.uca.systemfits.data.database.dao.SemanaDao
import ni.edu.uca.systemfits.data.database.dao.EjerciciosDao
import ni.edu.uca.systemfits.data.database.entities.*


@Database(
    entities = [Medidas::class, Comidas::class, Registros::class, SemanaEjercicios::class, Ejercicios::class],
    version = 5
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun medidasDao(): MedidasDao
    abstract fun ComidasDao(): ComidasDao
    abstract fun RegistrosDao(): RegistrosDao
    abstract fun SemanaDao(): SemanaDao
    abstract fun EjerciciosDao(): EjerciciosDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}