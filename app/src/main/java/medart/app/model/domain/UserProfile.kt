package medart.app.domain

data class UserProfile(
    val nombre: String,
    val apellido: String,
    val email: String,
    val telefono: String,
    val rut: String,
    val password: String
)
