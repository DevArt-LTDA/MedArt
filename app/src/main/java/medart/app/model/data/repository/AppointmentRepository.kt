package medart.app.model.data.repository

import kotlinx.coroutines.flow.Flow
import medart.app.model.data.dao.AppointmentDao
import medart.app.model.data.entities.AppointmentEntities

class AppointmentRepository(private val dao: AppointmentDao) {

    suspend fun insert(appointment: AppointmentEntities): Long {
        return dao.insertAppointment(appointment)
    }

    fun getAppointments(): Flow<List<AppointmentEntities>> {
        return dao.getFormularios()
    }

    suspend fun clear() = dao.deleteAll()
}
