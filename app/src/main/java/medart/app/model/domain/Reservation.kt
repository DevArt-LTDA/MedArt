package medart.app.domain

data class Reservation(
    val id: Int,
    val tipoAtencion: String = "Consulta médica",
    val especialidad: String,
    val horario: String
)
