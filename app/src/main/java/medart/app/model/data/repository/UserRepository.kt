package medart.app.model.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import medart.app.model.data.dao.UserDao
import medart.app.model.data.entities.UserEntities

class UserRepository(private val dao: UserDao) {

    suspend fun getUserByRut(rut: String): UserEntities? =
        withContext(Dispatchers.IO) { dao.getUserByRut(rut) }

    suspend fun updateUserByRut(
        currentRut: String,
        newRut: String,
        name: String,
        lastName: String,
        email: String,
        phone: String,
        passWord: String
    ): Int = withContext(Dispatchers.IO) {
        dao.updateUserByRut(
            currentRut = currentRut,
            newRut = newRut,
            name = name,
            lastName = lastName,
            email = email,
            phone = phone,
            passWord = passWord
        )
    }
}
