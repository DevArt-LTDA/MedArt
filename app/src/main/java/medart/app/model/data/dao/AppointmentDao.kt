package medart.app.model.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import medart.app.model.data.entities.AppointmentEntities

@Dao
interface AppointmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppointment(appointment: AppointmentEntities): Long

    @Query("SELECT * FROM Appointments ORDER BY id DESC")
    fun getAppointments(): Flow<List<AppointmentEntities>>

    @Query("DELETE FROM Appointments")
    suspend fun deleteAll()
}
