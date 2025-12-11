package medart.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import medart.app.model.data.entities.AppointmentEntities
import medart.app.model.data.repository.AppointmentRepository
import medart.app.model.domain.AppointmentUiState

class AppointmentViewModel(
    private val repository: AppointmentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AppointmentUiState())
    val uiState: StateFlow<AppointmentUiState> = _uiState.asStateFlow()

    // Opcional: listar reservas
    val reservas = repository.obtenerReservas()

    fun onPrevisionChange(value: String) {
        _uiState.value = _uiState.value.copy(
            prevision = value,
            errorPrevision = if (value.isBlank()) "La previsión es obligatoria" else null
        )
    }

    fun onEspecialidadChange(value: String) {
        _uiState.value = _uiState.value.copy(
            especialidad = value,
            errorEspecialidad = if (value.isBlank()) "La especialidad es obligatoria" else null
        )
    }

    fun onCentroChange(value: String) {
        _uiState.value = _uiState.value.copy(
            centro = value,
            errorCentro = if (value.isBlank()) "El centro médico es obligatorio" else null
        )
    }

    fun onFechaChange(value: String) {
        _uiState.value = _uiState.value.copy(
            fecha = value,
            errorFecha = if (value.isBlank()) "La fecha es obligatoria" else null
        )
    }

    fun onHoraChange(value: String) {
        _uiState.value = _uiState.value.copy(
            hora = value,
            errorHora = if (value.isBlank()) "La hora es obligatoria" else null
        )
    }

    fun onEnviarReserva() {
        val current = _uiState.value

        val validatedState = current.copy(
            errorPrevision = if (current.prevision.isBlank()) "La previsión es obligatoria" else null,
            errorEspecialidad = if (current.especialidad.isBlank()) "La especialidad es obligatoria" else null,
            errorCentro = if (current.centro.isBlank()) "El centro médico es obligatorio" else null,
            errorFecha = if (current.fecha.isBlank()) "La fecha es obligatoria" else null,
            errorHora = if (current.hora.isBlank()) "La hora es obligatoria" else null
        )

        _uiState.value = validatedState
        if (!validatedState.isFormValid) return

        viewModelScope.launch {
            val entity = AppointmentEntities(
                prevision = validatedState.prevision,
                especialidad = validatedState.especialidad,
                centroMedico = validatedState.centro,
                fecha = validatedState.fecha,
                hora = validatedState.hora
            )

            repository.insertAppointment(entity)
            _uiState.value = AppointmentUiState() // limpiar formulario
        }
    }
}
