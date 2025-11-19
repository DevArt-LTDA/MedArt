package medart.app.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.duoc.app.model.data.repository.RegisterRepository
import medart.app.model.data.config.AppDatabase

class HomeViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            val db = AppDatabase.AppDatabase.getDatabase(application)
            val repo = RegisterRepository(db.UserDao())
            return RegisterViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

