package medart.app.model.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import medart.app.model.data.entities.UserEntities
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertUser(user: UserEntities): Long

    @Query("SELECT * FROM USERS ORDER BY rut DESC")
    fun getFormularios(): Flow<List<UserEntities>>

    @Query("DELETE FROM Users")
    suspend fun deleteAll()

}