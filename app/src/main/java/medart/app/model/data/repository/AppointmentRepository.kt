package medart.app.model.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import medart.app.model.data.dao.AppointmentDao
import medart.app.model.data.entities.AppointmentEntities

class AppointmentRepository(private val dao: AppointmentDao) {

    fun obtenerReservas() = dao.getAppointments()

    suspend fun insertAppointment(entity: AppointmentEntities): Long =
        withContext(Dispatchers.IO) {
            dao.insertAppointment(entity)
        }

    suspend fun limpiar() =
        withContext(Dispatchers.IO) {
            dao.deleteAll()
        }
}
