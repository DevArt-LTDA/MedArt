package medart.app.model.domain

data class AppointmentUiState(
    val rut: String = "",
    val prevision: String = "",
    val especialidad: String = "",
    val centro: String = "",
    val fecha: String = "",
    val hora: String = "",
) {
    val isFormValid: Boolean
        get() = rut.isNotBlank()
                && prevision.isNotBlank()
                && especialidad.isNotBlank()
                && centro.isNotBlank()
                && fecha.isNotBlank()
                && hora.isNotBlank()
}
