package medart.app.model.domain

data class AppointmentUiState(
    val prevision: String = "",
    val especialidad: String = "",
    val centro: String = "",          // <- antes centroMedico
    val fecha: String = "",
    val hora: String = "",

    val errorPrevision: String? = null,
    val errorEspecialidad: String? = null,
    val errorCentro: String? = null,  // <- antes errorCentroMedico
    val errorFecha: String? = null,
    val errorHora: String? = null
) {
    val isFormValid: Boolean
        get() =
            prevision.isNotBlank() &&
                    especialidad.isNotBlank() &&
                    centro.isNotBlank() &&
                    fecha.isNotBlank() &&
                    hora.isNotBlank() &&
                    errorPrevision == null &&
                    errorEspecialidad == null &&
                    errorCentro == null &&
                    errorFecha == null &&
                    errorHora == null
}

