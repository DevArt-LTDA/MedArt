package medart.app.model.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class UserEntities(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,          // PK numérico autogenerado

    val rut: String,          // campo normal
    val name: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val passWord: String
)
