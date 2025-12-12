package medart.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import medart.app.model.data.config.AppDatabase
import medart.app.model.data.repository.AppointmentRepository
import medart.app.model.data.repository.RegisterRepository
import medart.app.model.data.repository.UserRepository
import medart.app.ui.screen.AppointmentScreen
import medart.app.ui.screen.HomeScreen
import medart.app.ui.screen.LoginScreen
import medart.app.ui.screen.ProfileScreen
import medart.app.ui.screen.RegisterScreen
import medart.app.ui.screen.RutScreen
import medart.app.viewmodel.AppointmentViewModel
import medart.app.viewmodel.AppointmentViewModelFactory
import medart.app.viewmodel.RegisterViewModel
import medart.app.viewmodel.RegisterViewModelFactory
import medart.app.viewmodel.UserProfileViewModel
import medart.app.viewmodel.UserProfileViewModelFactory

object Routes {
    const val RUT = "rut"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
    const val APPOINTMENT = "appointment"
    const val PROFILE = "profile"
}

@Composable
fun MedArtApp() {
    // ✅ FIX: crear navController
    val navController = rememberNavController()

    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }

    val userRepository = remember { UserRepository(db.userDao()) }
    val profileFactory = remember { UserProfileViewModelFactory(userRepository) }
    val profileVm: UserProfileViewModel = viewModel(factory = profileFactory)

    MedArtNavGraph(
        navController = navController,
        profileViewModel = profileVm
    )
}

@Composable
fun MedArtNavGraph(
    navController: NavHostController,
    profileViewModel: UserProfileViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.RUT
    ) {
        composable(Routes.RUT) {
            RutScreen(
                onContinue = { navController.navigate(Routes.LOGIN) }
            )
        }

        composable(Routes.LOGIN) {
            LoginScreen(
                onContinue = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.RUT) { inclusive = true }
                    }
                },
                onRegisterClick = { navController.navigate(Routes.REGISTER) }
            )
        }

        composable(Routes.REGISTER) {
            val context = LocalContext.current
            val db = remember { AppDatabase.getDatabase(context) }

            val repository = remember { RegisterRepository(db.userDao()) }
            val factory = remember { RegisterViewModelFactory(repository) }
            val registerViewModel: RegisterViewModel = viewModel(factory = factory)

            RegisterScreen(
                profileViewModel = profileViewModel,
                registerViewModel = registerViewModel,
                onRegistered = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.RUT) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.APPOINTMENT) {
            val context = LocalContext.current
            val db = remember { AppDatabase.getDatabase(context) }

            val repository = remember { AppointmentRepository(db.appointmentDao()) }
            val factory = remember { AppointmentViewModelFactory(repository) }
            val appointmentViewModel: AppointmentViewModel = viewModel(factory = factory)

            AppointmentScreen(
                appointmentViewModel = appointmentViewModel,
                onConfirmReserva = { navController.popBackStack(Routes.HOME, inclusive = false) },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.HOME) {
            HomeScreen(
                profileViewModel = profileViewModel,
                onConsultaMedicaClick = { navController.navigate(Routes.APPOINTMENT) },
                onExamenesClick = { /* TODO */ },
                onVerPerfil = { navController.navigate(Routes.PROFILE) },
                onLogout = { navController.navigate(Routes.RUT) }
            )
        }

        composable(Routes.PROFILE) {
            ProfileScreen(
                profileViewModel = profileViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
