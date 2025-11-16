package medart.app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import medart.app.domain.UserProfile
import medart.app.domain.Reservation

class UserProfileViewModel : ViewModel() {

    var profile by mutableStateOf<UserProfile?>(null)
        private set

    var reservations by mutableStateOf<List<Reservation>>(emptyList())
        private set

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

    fun addReservation(reservation: Reservation) {
        reservations = reservations + reservation
    }

    fun logout() {
        profile = null
        reservations = emptyList()
    }
}
