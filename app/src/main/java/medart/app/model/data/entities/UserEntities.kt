package medart.app.model.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")

data class UserEntities(
    @PrimaryKey(autoGenerate = true)
    val rut: String,
    val name: String,
    val lastName: String,
    val email: String,
    val phone: Int,
    val passWord: String
)