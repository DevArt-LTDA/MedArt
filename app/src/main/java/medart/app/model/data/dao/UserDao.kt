package medart.app.model.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import medart.app.model.data.entities.UserEntities

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntities): Long

    @Query("SELECT * FROM Users ORDER BY rut DESC")
    fun getFormularios(): Flow<List<UserEntities>>

    @Query("DELETE FROM Users")
    suspend fun deleteAll()

    // ===== NUEVO: obtener usuario por RUT =====
    @Query("SELECT * FROM Users WHERE rut = :rut LIMIT 1")
    suspend fun getUserByRut(rut: String): UserEntities?

    // ===== NUEVO: actualizar por RUT (permite cambiar el RUT) =====
    @Query("""
        UPDATE Users
        SET rut      = :newRut,
            name     = :name,
            lastName = :lastName,
            email    = :email,
            phone    = :phone,
            passWord = :passWord
        WHERE rut = :currentRut
    """)
    suspend fun updateUserByRut(
        currentRut: String,
        newRut: String,
        name: String,
        lastName: String,
        email: String,
        phone: String,
        passWord: String
    ): Int
}
