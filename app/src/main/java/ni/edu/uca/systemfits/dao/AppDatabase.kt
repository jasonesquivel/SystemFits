package ni.edu.uca.systemfits.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room
import ni.edu.uca.systemfits.modelo.Comidas
import ni.edu.uca.systemfits.modelo.Medidas


@Database(entities = [Medidas::class, Comidas::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun medidasDao(): MedidasDao
    abstract fun ComidasDao(): ComidasDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}