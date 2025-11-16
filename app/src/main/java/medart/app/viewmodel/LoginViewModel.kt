package medart.app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import medart.app.model.domain.LoginUIState

class LoginViewModel : ViewModel() {

    var uiState by mutableStateOf(LoginUIState())
        private set

    fun onRutChange(newRut: String) {
        uiState = uiState.copy(rut = newRut)
    }
//ejemplo
    fun onPrevisionChange(newPrev: String) {
        uiState = uiState.copy(previsionSalud = newPrev)
    }
}
