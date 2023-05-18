package ni.edu.uca.systemfits.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room
import ni.edu.uca.systemfits.modelo.Comidas
import ni.edu.uca.systemfits.modelo.Medidas
import ni.edu.uca.systemfits.modelo.Registros


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