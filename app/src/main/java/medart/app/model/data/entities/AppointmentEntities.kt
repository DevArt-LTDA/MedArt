package medart.app.model.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Appointments")

data class AppointmentEntities(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val prevision: String,
    val especialidad: String,
    val centro: String,
    val fecha: String,
    val hora: String
)
