// medart/app/viewmodel/AppointmentViewModel.kt
package medart.app.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AppointmentUiState(
    val prevision: String = "",
    val especialidad: String = "",
    val centro: String = "",
    val fecha: String = "",
    val hora: String = "",
) {
    val isFormValid: Boolean
        get() = prevision.isNotBlank() &&
                especialidad.isNotBlank() &&
                centro.isNotBlank() &&
                fecha.isNotBlank() &&
                hora.isNotBlank()
}

class AppointmentViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AppointmentUiState())
    val uiState: StateFlow<AppointmentUiState> = _uiState.asStateFlow()

    fun onPrevisionChange(value: String) {
        _uiState.update { it.copy(prevision = value) }
    }

    fun onEspecialidadChange(value: String) {
        _uiState.update { it.copy(especialidad = value) }
    }

    fun onCentroChange(value: String) {
        _uiState.update { it.copy(centro = value) }
    }

    fun onFechaChange(value: String) {
        _uiState.update { it.copy(fecha = value) }
    }

    fun onHoraChange(value: String) {
        _uiState.update { it.copy(hora = value) }
    }

    fun onEnviarReserva() {
        // De momento solo valida / podría hacer log, etc.
    }
}
