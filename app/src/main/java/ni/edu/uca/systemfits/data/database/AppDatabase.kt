package ni.edu.uca.systemfits.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room
import ni.edu.uca.systemfits.data.database.dao.ComidasDao
import ni.edu.uca.systemfits.data.database.dao.MedidasDao
import ni.edu.uca.systemfits.data.database.dao.RegistrosDao
import ni.edu.uca.systemfits.data.database.entities.Comidas
import ni.edu.uca.systemfits.data.database.entities.Medidas
import ni.edu.uca.systemfits.data.database.entities.Registros


@Database(entities = [Medidas::class, Comidas::class, Registros::class], version = 3)
abstract class AppDatabase : RoomDatabase() {

    abstract fun medidasDao(): MedidasDao
    abstract fun ComidasDao(): ComidasDao
    abstract fun RegistrosDao(): RegistrosDao

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