package medart.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import medart.app.model.domain.RegisterUIState
import medart.app.model.data.entities.UserEntities
import medart.app.model.data.repository.
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RegisterUiState(
    val nombre: String = "",
    val apellido: String = "",
    val email: String = "",
    val telefono: String = "",      // <- SIEMPRE STRING PARA EL TEXTFIELD
    val rut: String = "",
    val password: String = "",

    val errorNombre: String? = null,
    val errorApellido: String? = null,
    val errorEmail: String? = null,
    val errorTelefono: String? = null,
    val errorRut: String? = null,
    val errorPassword: String? = null
) {
    val isFormValid: Boolean
        get() =
            errorNombre == null &&
                    errorApellido == null &&
                    errorEmail == null &&
                    errorTelefono == null &&
                    errorRut == null &&
                    errorPassword == null &&
                    nombre.isNotBlank() &&
                    apellido.isNotBlank() &&
                    email.isNotBlank() &&
                    telefono.isNotBlank() &&
                    rut.isNotBlank() &&
                    password.isNotBlank()
}

class RegisterViewModel(repo: RegisterRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun onNombreChange(value: String) {
        _uiState.value = _uiState.value.copy(
            nombre = value,
            errorNombre = if (value.isBlank()) "El nombre es obligatorio" else null
        )
    }

    fun onApellidoChange(value: String) {
        _uiState.value = _uiState.value.copy(
            apellido = value,
            errorApellido = if (value.isBlank()) "El apellido es obligatorio" else null
        )
    }

    fun onEmailChange(value: String) {
        val regex = Regex("^[^@]+@[^@]+\\.[^@]+$")
        _uiState.value = _uiState.value.copy(
            email = value,
            errorEmail = if (value.isBlank()) {
                "El correo es obligatorio"
            } else if (!regex.matches(value)) {
                "Correo electrónico inválido"
            } else null
        )
    }

    fun onTelefonoChange(value: String) {
        val digitsOnly = value.filter { it.isDigit() }

        _uiState.value = _uiState.value.copy(
            telefono = digitsOnly, // solo números
            errorTelefono = when {
                digitsOnly.isBlank() -> "El teléfono es obligatorio"
                digitsOnly.length < 8 -> "Debe tener al menos 8 dígitos"
                else -> null
            }
        )
    }

    fun onRutChange(value: String) {
        val clean = value.uppercase()
        val rutRegex = Regex("^[0-9]{7,8}[0-9Kk]\$")
        _uiState.value = _uiState.value.copy(
            rut = clean,
            errorRut = if (!rutRegex.matches(clean)) "RUT inválido (ej: 20345678K)" else null
        )
    }

    fun onPasswordChange(value: String) {
        _uiState.value = _uiState.value.copy(
            password = value,
            errorPassword =
                if (value.length < 6) "La contraseña debe tener al menos 6 caracteres"
                else null
        )
    }

    private val _estado = MutableStateFlow(RegisterUiState())
    val estado: StateFlow<RegisterUiState> = _estado.asStateFlow()

    fun onEnviarFormulario() {
        val ui = _estado.value

        // Validaciones básicas
        val errores = ui.errores.copy(
            nombreCliente = if (ui.nombre.isBlank()) "El nombre es obligatorio" else null,
            correoCliente = if (ui.email.isBlank()) "El correo es obligatorio" else null
        )

        // Actualiza errores en UI
        _estado.update { it.copy(errores = errores) }

        // Si hay errores, no persistir
        if (errores.tieneErrores()) return

        // Persistir en SQLite (Room)
        viewModelScope.launch {
            val entity = UserEntities(
                nombre = ui,
                email = ui.email,

            )
            repository.insert(entity)

            // Opcional: limpiar formulario
            _estado.update { RegisterUiState() }
        }
    }


}

