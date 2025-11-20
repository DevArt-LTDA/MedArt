package medart.app.model.data.repository

import medart.app.model.data.dao.UserDao
import medart.app.model.data.entities.UserEntities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RegisterRepository(private val dao: UserDao) {

    fun obtenerFormularios() = dao.getFormularios()

    // Metodo usado por el ViewModel
    suspend fun registerUser(user: UserEntities): Long =
        withContext(Dispatchers.IO) {
            dao.insertUser(user)
        }

    suspend fun insertUser(entity: UserEntities): Long =
        withContext(Dispatchers.IO) {
            dao.insertUser(entity)
        }

    suspend fun limpiar() =
        withContext(Dispatchers.IO) {
            dao.deleteAll()
        }
}
