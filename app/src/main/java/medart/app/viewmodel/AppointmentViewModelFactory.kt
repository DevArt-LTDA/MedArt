package medart.app.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import medart.app.model.data.config.AppDatabase
import medart.app.model.data.repository.AppointmentRepository

class AppointmentViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppointmentViewModel::class.java)) {
            val db = AppDatabase.getDatabase(application)
            val repo = AppointmentRepository(db.appointmentDao())

            return AppointmentViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
