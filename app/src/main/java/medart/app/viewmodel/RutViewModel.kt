package medart.app.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class RutUiState(
    val rut: String = ""
) {
    // Aquí vive la VALIDACIÓN
    val isRutValid: Boolean
        get() = Regex("^[0-9]{7,8}[0-9Kk]\$").matches(rut)
}

class RutViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RutUiState())
    val uiState: StateFlow<RutUiState> = _uiState

    fun onRutChange(newRut: String) {
        _uiState.value = _uiState.value.copy(
            rut = newRut.uppercase()
        )
    }
}
