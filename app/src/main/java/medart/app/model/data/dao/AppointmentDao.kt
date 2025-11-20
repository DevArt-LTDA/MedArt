package medart.app.model.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import medart.app.model.data.entities.AppointmentEntities
import kotlinx.coroutines.flow.Flow

@Dao
interface AppointmentDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAppointment(appointment: AppointmentEntities): Long

    @Query("SELECT * FROM APPOINTMENTS ORDER BY rut DESC")
    fun getFormularios(): Flow<List<AppointmentEntities>>

    @Query("DELETE FROM Appointments")
    suspend fun deleteAll()

}
