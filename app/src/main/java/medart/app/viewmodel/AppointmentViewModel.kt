package medart.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import medart.app.model.data.entities.AppointmentEntities
import medart.app.model.data.repository.AppointmentRepository
import medart.app.model.domain.AppointmentUiState

class AppointmentViewModel(
    private val repository: AppointmentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AppointmentUiState())
    val uiState: StateFlow<AppointmentUiState> = _uiState

    // --- Cambios por campo ---

    fun onRutChange(value: String) {
        _uiState.value = _uiState.value.copy(rut = value)
    }

    fun onPrevisionChange(value: String) {
        _uiState.value = _uiState.value.copy(prevision = value)
    }

    fun onEspecialidadChange(value: String) {
        _uiState.value = _uiState.value.copy(especialidad = value)
    }

    fun onCentroChange(value: String) {
        _uiState.value = _uiState.value.copy(centro = value)
    }

    fun onFechaChange(value: String) {
        _uiState.value = _uiState.value.copy(fecha = value)
    }

    fun onHoraChange(value: String) {
        _uiState.value = _uiState.value.copy(hora = value)
    }

    // --- Guardar en Room ---

    fun onEnviarReserva() {
        val state = _uiState.value
        if (!state.isFormValid) return

        val newReserva = AppointmentEntities(
            rut = state.rut,
            prevision = state.prevision,
            especialidad = state.especialidad,
            centroMedico = state.centro,
            fecha = state.fecha,
            hora = state.hora
        )

        viewModelScope.launch {
            repository.insert(newReserva)
        }
    }
}
