package ni.edu.uca.systemfits.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room
import ni.edu.uca.systemfits.modelo.Medidas

//PARA QUE AGREGUEN SUS ENTIDADES SOLO PONGAN UNA COMDA
//DESPUES DE DONDE DICE ::class//
@Database(entities = [Medidas::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun medidasDao(): MedidasDao

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