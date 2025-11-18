package medart.app.model.data.config

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import medart.app.model.data.dao.FormularioServicioDao
import medart.app.model.data.entities.FormularioServicioEntity

class AppDatabase {
    @Database(
        entities = [FormularioServicioEntity::class],
        version = 1,
        exportSchema = false
    )
    abstract class AppDatabase : RoomDatabase() {

        abstract fun formularioServicioDao(): FormularioServicioDao

        companion object {
            @Volatile
            private var INSTANCE: AppDatabase? = null

            fun getDatabase(context: Context): AppDatabase {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_db"
                    ).build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }



}