package medart.app.model.domain

data class RegisterUIState(
    val rut: String = "",
    val name: String = "",
    val lastName: String = "",
    val email: String = "",
    val phone: String = "",
    val passWord: String = ""
)
