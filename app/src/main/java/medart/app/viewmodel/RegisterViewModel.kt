package medart.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import medart.app.model.data.entities.UserEntities
import medart.app.model.data.repository.RegisterRepository

data class RegisterUiState(
    val nombre: String = "",
    val apellido: String = "",
    val email: String = "",
    val telefono: String = "",      // Siempre String para el TextField
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

class RegisterViewModel(
    private val repository: RegisterRepository   // se guarda en una propiedad
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

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
            errorEmail = when {
                value.isBlank() -> "El correo es obligatorio"
                !regex.matches(value) -> "Correo electrónico inválido"
                else -> null
            }
        )
    }

    fun onTelefonoChange(value: String) {
        val digitsOnly = value.filter { it.isDigit() }

        _uiState.value = _uiState.value.copy(
            telefono = digitsOnly,
            errorTelefono = when {
                digitsOnly.isBlank() -> "El teléfono es obligatorio"
                digitsOnly.length < 8 -> "Debe tener al menos 8 dígitos"
                else -> null
            }
        )
    }

    fun onRutChange(value: String) {
        val clean = value.uppercase()
        val rutRegex = Regex("^[0-9]{7,8}[0-9K]\$")

        _uiState.value = _uiState.value.copy(
            rut = clean,
            errorRut =
                if (!rutRegex.matches(clean))
                    "RUT inválido (ej: 20345678K)"
                else null
        )
    }

    fun onPasswordChange(value: String) {
        _uiState.value = _uiState.value.copy(
            password = value,
            errorPassword =
                if (value.length < 6)
                    "La contraseña debe tener al menos 6 caracteres"
                else null
        )
    }

    fun onEnviarFormulario() {
        val current = _uiState.value

        // Revalidar por seguridad antes de enviar
        val nombreError =
            if (current.nombre.isBlank()) "El nombre es obligatorio" else null
        val apellidoError =
            if (current.apellido.isBlank()) "El apellido es obligatorio" else null
        val emailError =
            if (current.email.isBlank()) "El correo es obligatorio" else null
        val telefonoError =
            if (current.telefono.isBlank()) "El teléfono es obligatorio" else null
        val rutError =
            if (current.rut.isBlank()) "El RUT es obligatorio" else null
        val passwordError =
            if (current.password.length < 6) "La contraseña debe tener al menos 6 caracteres" else null

        val validatedState = current.copy(
            errorNombre = nombreError,
            errorApellido = apellidoError,
            errorEmail = emailError,
            errorTelefono = telefonoError,
            errorRut = rutError,
            errorPassword = passwordError
        )

        _uiState.value = validatedState

        if (!validatedState.isFormValid) return

        viewModelScope.launch {
            val entity = UserEntities(
                name = validatedState.nombre,
                lastName = validatedState.apellido,
                email = validatedState.email,
                passWord = validatedState.password,
                phone = validatedState.telefono,
                rut = validatedState.rut
            )

            // Aquí llamas al backend / repositorio
            repository.registerUser(entity)

            // Limpiar formulario tras registro exitoso
            _uiState.value = RegisterUiState()
        }
    }
}
