package medart.app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import medart.app.domain.Reservation
import medart.app.domain.UserProfile
import medart.app.model.data.repository.UserRepository

class UserProfileViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    var profile by mutableStateOf<UserProfile?>(null)
        private set

    var reservations by mutableStateOf<List<Reservation>>(emptyList())
        private set

    // Se mantiene para compatibilidad con su RegisterScreen actual



    fun saveProfile(
        nombre: String,
        apellido: String,
        email: String,
        telefono: String,
        rut: String,
        password: String
    ) {
        profile = UserProfile(
            nombre = nombre,
            apellido = apellido,
            email = email,
            telefono = telefono,
            rut = rut,
            password = password
        )
    }

    // ===== NUEVO: actualiza UI + DB =====


    fun updateProfile(
        currentRut: String,
        newRut: String,
        nombre: String,
        apellido: String,
        email: String,
        telefono: String
    ) {
        val current = profile ?: return

        // 1) Actualizar estado UI inmediatamente
        val updated = current.copy(
            nombre = nombre,
            apellido = apellido,
            email = email,
            telefono = telefono,
            rut = newRut
        )
        profile = updated

        // 2) Persistir en Room/SQLite
        viewModelScope.launch {
            userRepository.updateUserByRut(
                currentRut = currentRut,
                newRut = newRut,
                name = nombre,
                lastName = apellido,
                email = email,
                phone = telefono,
                passWord = current.password
            )
        }
    }

    fun addReservation(reservation: Reservation) {
        reservations = reservations + reservation
    }

    fun loadProfileByRut(rut: String) {
        viewModelScope.launch {
            val entity = userRepository.getUserByRut(rut.trim())
            if (entity != null) {
                profile = UserProfile(
                    nombre = entity.name,
                    apellido = entity.lastName,
                    email = entity.email,
                    telefono = entity.phone,
                    rut = entity.rut,
                    password = entity.passWord
                )
            } else {
                profile = null
            }
        }
    }


    fun logout() {
        profile = null
        reservations = emptyList()
    }
}
