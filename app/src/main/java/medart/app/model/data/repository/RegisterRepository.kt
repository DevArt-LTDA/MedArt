package cl.duoc.app.model.data.repository


import medart.app.model.data.dao.UserDao
import medart.app.model.data.entities.UserEntities

class RegisterRepository(private val dao: UserDao) {

    fun obtenerFormularios() = dao.getFormularios()

    suspend fun insertUser(entity: UserEntities): Long {
        return dao.insertUser(entity)
    }

    suspend fun limpiar() = dao.deleteAll()
}
