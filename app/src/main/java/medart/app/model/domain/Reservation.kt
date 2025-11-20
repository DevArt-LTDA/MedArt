package medart.app.model.domain

data class Reservation(
    val id: Long = 0,
    val rut: String = "",
    val prevision: String = "",
    val especialidad: String = "",
    val centroMedico: String = "",
    val fecha: String = "",
    val hora: String = ""
)
